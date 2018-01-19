package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.AbstractNavigationElement;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.interfaces.Provider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftClickableNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;
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
    private ComponentNavigator componentNavigator;
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

    private AppLayoutComponent instance;

    private Provider<String, String> viewNameInterceptor = null;
    private Provider<String, String> captionInterceptor;
    private boolean CDI = false;
    private boolean scrollToTopOnNavigate = true;
    private boolean closeSubmenusOnNavigate = true;
    private boolean navigatorEnabled = true;
    private Component titleComponent;

    public AppLayoutConfiguration(AppLayoutComponent instance) {
        this.instance = instance;
    }

    public AppLayoutComponent build() {

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
        if (titleComponent == null) {
            instance.setTitle(title);
        } else {
            instance.setTitleComponent(titleComponent);
        }
        if (navigatorEnabled) {
            navigator = navigatorProducer.apply(instance.getContentHolder());
            if (navigator == null) {
                throw new RuntimeException("The set navigatorProducer returned 'null' as a Navigator");
            }

            navigator.addViewChangeListener(event -> beforeViewChange(event.getViewName()));
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
        } else {
            componentNavigator = new ComponentNavigator(instance.getContentHolder());
            componentNavigator.addViewChangeListener(event -> beforeViewChange(event.getViewName()));
            if (errorViewProvider != null) {
                componentNavigator.setErrorView(errorViewProvider.get());
            }
            if (defaultNavigationElement == null) {
                defaultNavigationElement = navigationElements.stream()
                        .filter(element -> element instanceof NavigatorNavigationElement)
                        .map(element -> ((NavigatorNavigationElement) element)).findFirst().orElse(null);
            }
            defaultNavigationElement.addViewToComponentNavigator(componentNavigator);
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
        if (!navigatorEnabled) {
            componentNavigator.navigateTo(defaultNavigationElement.getViewName());
        }
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
            if (navigatorEnabled) {
                nElement.addViewToNavigator(navigator);
            } else {
                nElement.addViewToComponentNavigator(componentNavigator);
            }
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

    public void add(AbstractNavigationElement element, Position position) {
        addToPosition(element, position);
    }

    public void addToPosition(AbstractNavigationElement element, Position position) {
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

    public void setNavigatorEnabled(boolean navigatorEnabled) {
        this.navigatorEnabled = navigatorEnabled;
    }

    public ComponentNavigator getComponentNavigator() {
        return componentNavigator;
    }

    public void setScrollToTopOnNavigate(boolean scrollToTopOnNavigate) {
        this.scrollToTopOnNavigate = scrollToTopOnNavigate;
    }

    public void setCloseSubmenusOnNavigate(boolean closeSubmenusOnNavigate) {
        this.closeSubmenusOnNavigate = closeSubmenusOnNavigate;
    }

    private boolean beforeViewChange(String viewName) {
        AppLayoutSessionHelper.removeStyleFromCurrentlyActiveNavigationElement();
        Optional<NavigatorNavigationElement> result = findNextNavigationElement(viewName);
        if (result.isPresent()) {
            result.ifPresent(element -> {
                AppLayoutSessionHelper.setActiveNavigationElement(element);
                if (closeSubmenusOnNavigate) {
                    navigationElements.stream()
                            .filter(nelement -> nelement instanceof SubmenuNavigationElement)
                            .filter(nelement -> nelement != element)
                            .map(nElement -> (SubmenuNavigationElement) nElement)
                            .forEach(submenuNavigationElement -> submenuNavigationElement.closeEventually(element));
                }
                if (scrollToTopOnNavigate) {
                    instance.getContentHolder().setScrollTop(0);
                }
            });
        }
        return true;
    }

    public void setTitleComponent(Component titleComponent) {
        this.titleComponent = titleComponent;
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

    public enum Position {
        HEADER, DEFAULT, FOOTER
    }

}
