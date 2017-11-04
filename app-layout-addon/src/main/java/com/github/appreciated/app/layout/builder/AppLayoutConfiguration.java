package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftCustomNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.session.AppLayoutSessionHelper;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class AppLayoutConfiguration {

    Behaviour variant = Behaviour.LEFT;
    List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private NavigatorConsumer navigatorConsumer;
    private NavigatorProducer navigatorProducer = components -> new Navigator(UI.getCurrent(), components);
    private AppBarDesign design = AppBarDesign.DEFAULT;
    private Navigator navigator;
    private String title;
    private NavigatorNavigationElement defaultNavigationElement;
    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider;
    private DefaultLeftCustomNavigationElementProvider customElementProvider;
    private ComponentProvider<Component, SectionNavigationElement> sectionProvider;
    private ComponentProvider<Component, SubmenuNavigationElement> submenuProvider;
    private NavigationElementInfoProvider navigationElementInfoProvider = null;
    private List<AbstractNavigationElement> footerElements = new ArrayList<>();
    private List<AbstractNavigationElement> headerElements = new ArrayList<>();
    private List<NavigatorNavigationElement> navigatorElements = new ArrayList<>();
    private Component appBarIconComponent;
    private boolean requiresNavigator = false;

    private AppLayout instance;

    private Provider<String, String> viewNameInterceptor = null;
    private Provider<String, String> captionInterceptor;

    public AppLayoutConfiguration(AppLayout instance) {
        this.instance = instance;
    }

    public AppLayout build() {
        AppLayoutSessionHelper.setActiveVariant(variant);
        if (navigationElementProvider == null) {
            if (variant.isTop())
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
            else
                navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
        }
        if (customElementProvider == null) {
            if (variant.isTop())
                customElementProvider = new DefaultLeftCustomNavigationElementProvider();
            else
                customElementProvider = new DefaultLeftCustomNavigationElementProvider();
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

        setTitle(title);
        if (true) {
            navigator = navigatorProducer.apply(instance.getContentHolder());
            navigator.addViewChangeListener(viewChangeEvent -> {
                AppLayoutSessionHelper.removeStyleFromCurrentlyActiveNavigationElement();
                findNextNavigationElement(viewChangeEvent.getViewName()).ifPresent(element -> AppLayoutSessionHelper.setActiveNavigationElement(element));
                return true;
            });
            if (navigatorConsumer != null) {
                navigatorConsumer.accept(navigator);
            }
            if (defaultNavigationElement == null) {
                defaultNavigationElement = navigationElements.stream()
                        .filter(element -> element instanceof NavigatorNavigationElement)
                        .map(element -> ((NavigatorNavigationElement) element)).findFirst().orElse(null);
            }
            defaultNavigationElement.addViewToNavigator(navigator);
        }
        addComponents(headerElements, instance::addNavigationHeaderElement);
        addComponents(navigationElements, instance::addNavigationElement);
        addComponents(footerElements, instance::addNavigationFooterElement);
        appBarElements.forEach(instance::addAppBarElement);
        setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        instance.setNavigatorNavigationElements(navigatorElements);
        return instance;
    }

    private Optional<NavigatorNavigationElement> findNextNavigationElement(String viewName) {
        if (viewName.equals("")) {
            return navigatorElements.stream()
                    .filter(element -> element.getViewClassName().equals(defaultNavigationElement.getViewClassName()) || element.getViewName().equals(""))
                    .findFirst();
        }
        return navigatorElements.stream()
                .filter(element -> element instanceof NavigatorNavigationElement)
                .filter(element -> element.getViewName().equals(viewName))
                .findFirst();
    }

    private void addComponents(List<AbstractNavigationElement> elements, ComponentConsumer consumer) {
        elements.forEach(element -> {
            setComponentProviders(element);
            consumer.accept(element);
        });
    }

    private void setComponentProviders(AbstractNavigationElement element) {
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement nelement = (NavigatorNavigationElement) element;
            nelement.setNavigationElementInfoProvider(navigationElementInfoProvider);
            if (nelement.getViewClassName() == defaultNavigationElement.getViewClassName()) {
                AppLayoutSessionHelper.updateActiveElementSessionData(nelement);
            }
            navigatorElements.add(nelement);
            nelement.setViewNameInterceptor(viewNameInterceptor);
            nelement.setCaptionInterceptor(captionInterceptor);
            nelement.addViewToNavigator(navigator);
        } else if (element instanceof ClickableNavigationElement) {
            ClickableNavigationElement cnelement = (ClickableNavigationElement) element;
        } else if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement selement = (SubmenuNavigationElement) element;
            selement.setCaptionInterceptor(captionInterceptor);
            selement.getSubmenuElements().forEach(element1 -> setComponentProviders(element1));
        } else if (element instanceof SectionNavigationElement) {
            SectionNavigationElement selement = (SectionNavigationElement) element;
            selement.setCaptionInterceptor(captionInterceptor);
        }
    }

    public void add(AbstractNavigationElement element, AppLayoutBuilder.Position position) {
        if (element instanceof NavigatorNavigationElement) {
            requiresNavigator = true;
        } else if (element instanceof SubmenuNavigationElement && ((SubmenuNavigationElement) element).requiresNavigator()) {
            requiresNavigator = ((SubmenuNavigationElement) element).requiresNavigator();
        }
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

    public void setNavigatorConsumer(NavigatorConsumer navigatorConsumer) {
        this.navigatorConsumer = navigatorConsumer;
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

    public void setSubmenuProvider(ComponentProvider<Component, SubmenuNavigationElement> submenuProvider) {
        this.submenuProvider = submenuProvider;
    }

    public void setNavigationElementInfoProvider(NavigationElementInfoProvider navigationElementInfoProvider) {
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

    @FunctionalInterface
    public interface NavigatorConsumer extends Consumer<Navigator> {
    }

    @FunctionalInterface
    public interface NavigatorProducer extends Function<Panel, Navigator> {
    }

    @FunctionalInterface
    public interface NavigationElementInfoProvider extends Function<Class<? extends View>, NavigationElementInfo> {
    }

    @FunctionalInterface
    interface ComponentConsumer extends Consumer<AbstractNavigationElement> {
    }

}
