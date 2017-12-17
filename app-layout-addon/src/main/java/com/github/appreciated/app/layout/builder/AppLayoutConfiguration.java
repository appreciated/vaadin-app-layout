package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftClickableNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.session.AppLayoutSessionHelper;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Since the class AppLayoutBuilder was grew so large I decided to outsource the logic to configure an AppLayout instance into this class.
 */
public class AppLayoutConfiguration {

    Behaviour variant = Behaviour.LEFT;
    List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private Consumer<Navigator> navigatorConsumer;
    private Supplier<ViewProvider> viewProviderSupplier;
    private Supplier<ViewProvider> errorProvider;
    private Supplier<View> errorViewProvider;

    private NavigatorProducer navigatorProducer = components -> new Navigator(UI.getCurrent(), components);
    private AppBarDesign design = AppBarDesign.DEFAULT;
    private Navigator navigator;
    private String title;
    private NavigatorNavigationElement defaultNavigationElement;
    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider;
    private DefaultLeftClickableNavigationElementProvider customElementProvider;
    private ComponentProvider<Component, SectionNavigationElement> sectionProvider;
    private ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> submenuProvider;
    private NavigationElementInfoProducer navigationElementInfoProvider = null;
    private List<AbstractNavigationElement> footerElements = new ArrayList<>();
    private List<AbstractNavigationElement> headerElements = new ArrayList<>();
    private List<NavigatorNavigationElement> navigatorElements = new ArrayList<>();
    private Component appBarIconComponent;

    private AppLayout instance;

    private Provider<String, String> viewNameInterceptor = null;
    private Provider<String, String> captionInterceptor;
    private boolean CDI;

    public AppLayoutConfiguration(AppLayout instance) {
        this.instance = instance;
    }

    public AppLayout build() {

        if (navigationElementProvider == null) {
            if (variant.isTop())
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
            else
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
        }
        if (customElementProvider == null) {
            if (variant.isTop())
                customElementProvider = new DefaultLeftClickableNavigationElementProvider();
            else
                customElementProvider = new DefaultLeftClickableNavigationElementProvider();
        }
        if (sectionProvider == null) {
            if (variant.isTop())
                sectionProvider = new DefaultLeftSectionElementComponentProvider();
            else
                sectionProvider = new DefaultLeftSectionElementComponentProvider();
        }
        if (submenuProvider == null) {
            if (variant.isTop())
                submenuProvider = new DefaultTopSubmenuNavigationElementProvider();
            else
                submenuProvider = new DefaultLeftSubmenuNavigationElementProvider();
        }

        AppLayoutSessionHelper.setActiveVariant(variant);

        setTitle(title);

        navigator = navigatorProducer.apply(instance.getContentHolder());
        navigator.addViewChangeListener(viewChangeEvent -> {
            AppLayoutSessionHelper.removeStyleFromCurrentlyActiveNavigationElement();
            findNextNavigationElement(viewChangeEvent.getViewName()).ifPresent(element -> {
                AppLayoutSessionHelper.setActiveNavigationElement(element);
                navigationElements.stream()
                        .filter(nelement -> nelement instanceof SubmenuNavigationElement)
                        .filter(nelement -> nelement != element)
                        .map(nElement -> (SubmenuNavigationElement) nElement)
                        .forEach(submenuNavigationElement -> submenuNavigationElement.closeEventually(element));
            });
            return true;
        });
        if (viewProviderSupplier != null) {
            navigator.addProvider(viewProviderSupplier.get());
        }
        if (errorProvider != null) {
            navigator.setErrorProvider(errorProvider.get());
        }
        if (errorViewProvider != null) {
            navigator.setErrorView(errorViewProvider.get());
        }
        if (navigatorConsumer != null) {
            navigatorConsumer.accept(navigator);
        }
        if (!CDI) {
            if (defaultNavigationElement == null) {
                defaultNavigationElement = navigationElements.stream()
                        .filter(element -> element instanceof NavigatorNavigationElement)
                        .map(element -> ((NavigatorNavigationElement) element)).findFirst().orElse(null);
            }
            defaultNavigationElement.addViewToNavigator(navigator);
        } else if (CDI && defaultNavigationElement != null) {
            System.err.println("WARNING - AppLayout - You are using CDI but try to set the DefaultNavigationElement this will have no effect");
        }
        addComponents(headerElements, instance::addNavigationHeaderElement);
        addComponents(navigationElements, instance::addNavigationElement);
        addComponents(footerElements, instance::addNavigationFooterElement);
        appBarElements.forEach(instance::addAppBarElement);
        instance.setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        instance.setNavigatorNavigationElements(navigatorElements);
        return instance;
    }

    private Optional<NavigatorNavigationElement> findNextNavigationElement(String viewName) {
        if (viewName.equals("")) {
            return navigatorElements.stream()
                    .filter(element -> (defaultNavigationElement != null && element.getViewClassName().equals(defaultNavigationElement.getViewClassName())) || element.getViewName().equals(""))
                    .findFirst();
        }
        return navigatorElements.stream()
                .filter(element -> element instanceof NavigatorNavigationElement)
                .filter(element -> element.getViewName().equals(viewName))
                .findFirst();
    }

    private void addComponents(List<AbstractNavigationElement> elements, ComponentConsumer consumer) {
        elements.forEach(element -> {
            prepareNavigationElement(element);
            consumer.accept(element);
        });
    }

    private void prepareNavigationElement(AbstractNavigationElement element) {
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement nElement = (NavigatorNavigationElement) element;
            nElement.setCDI(CDI);
            nElement.setNavigationElementInfoProvider(navigationElementInfoProvider);
            if ((CDI == false && nElement.getViewClassName() == defaultNavigationElement.getViewClassName()) ||
                    (CDI == true && nElement.getViewName().equals(""))) {
                AppLayoutSessionHelper.updateActiveElementSessionData(nElement);
            }
            nElement.setViewNameInterceptor(viewNameInterceptor);
            nElement.setCaptionInterceptor(captionInterceptor);
            nElement.addViewToNavigator(navigator);
            navigatorElements.add(nElement);
        } else if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement sElement = (SubmenuNavigationElement) element;
            sElement.setCaptionInterceptor(captionInterceptor);
            sElement.getSubmenuElements().forEach(element1 -> prepareNavigationElement(element1));
        } else if (element instanceof SectionNavigationElement) {
            SectionNavigationElement sElement = (SectionNavigationElement) element;
            sElement.setCaptionInterceptor(captionInterceptor);
        }
    }

    public void add(AbstractNavigationElement element, AppLayoutBuilder.Position position) {
        addToPosition(element, position);
    }

    public void addToPosition(AbstractNavigationElement element, AppLayoutBuilder.Position position) {
        switch (position) {
            case HEADER:
                headerElements.add(element);
                break;
            case DEFAULT:
                navigationElements.add(element);
                break;
            case FOOTER:
                footerElements.add(element);
                break;
        }
    }

    /***** Getters and Setters *****/

    public List<AbstractNavigationElement> getNavigationElements() {
        return navigationElements;
    }

    public AppBarDesign getDesign() {
        return design;
    }

    public void setDesign(AppBarDesign design) {
        this.design = design;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Component> getAppBarElements() {
        return appBarElements;
    }

    public void setNavigatorConsumer(Consumer<Navigator> navigatorConsumer) {
        this.navigatorConsumer = navigatorConsumer;
    }

    public void setViewProviderSupplier(Supplier<ViewProvider> viewProviderSupplier) {
        this.viewProviderSupplier = viewProviderSupplier;
    }

    public void setErrorProvider(Supplier<ViewProvider> errorProvider) {
        this.errorProvider = errorProvider;
    }

    public void setErrorView(Supplier<View> errorView) {
        this.errorViewProvider = errorView;
    }

    public void setNavigatorProducer(NavigatorProducer navigatorProducer) {
        this.navigatorProducer = navigatorProducer;
    }

    public void setDefaultNavigationElement(NavigatorNavigationElement defaultNavigationElement) {
        this.defaultNavigationElement = defaultNavigationElement;
    }

    public void setNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider) {
        this.navigationElementProvider = navigationElementProvider;
    }

    public void setSectionProvider(ComponentProvider<Component, SectionNavigationElement> sectionProvider) {
        this.sectionProvider = sectionProvider;
    }

    public void setSubmenuProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> submenuProvider) {
        this.submenuProvider = submenuProvider;
    }

    public void setNavigationElementInfoProvider(NavigationElementInfoProducer navigationElementInfoProvider) {
        this.navigationElementInfoProvider = navigationElementInfoProvider;
    }

    public void setAppBarIconComponent(Component appBarIconComponent) {
        this.appBarIconComponent = appBarIconComponent;
    }

    public void setViewNameInterceptor(Provider<String, String> viewNameInterceptor) {
        this.viewNameInterceptor = viewNameInterceptor;
    }

    public void setCaptionInterceptor(Provider<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }

    public void setCDI(boolean CDI) {
        this.CDI = CDI;
    }

    @FunctionalInterface
    public interface NavigatorProducer extends Function<Panel, Navigator> {
    }

    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends View>, NavigationElementInfo> {
    }

    @FunctionalInterface
    interface ComponentConsumer extends Consumer<AbstractNavigationElement> {
    }

}
