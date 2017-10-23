package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AbstractLeftAppLayout;
import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.behaviour.impl.LeftFallBack;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.providers.DefaultCustomNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.DefaultNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.DefaultSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.DefaultSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.interceptor.ViewNameInterceptor;
import com.github.appreciated.app.layout.session.AppLayoutSessionHelper;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppLayoutBuilder {

    Behaviour variant = Behaviour.LEFT;
    List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private NavigatorConsumer navigatorConsumer;
    private NavigatorProducer navigatorProducer = components -> new Navigator(UI.getCurrent(), components);
    private boolean requiresNavigatior = false;
    private AppBarDesign design = AppBarDesign.DEFAULT;
    private Navigator navigator;
    private String title;
    private AppLayout instance = null;
    private NavigatorNavigationElement defaultNavigationElement;
    private ComponentProvider<NavigatorNavigationElement> navigationElementProvider = new DefaultNavigationBadgeElementComponentProvider();
    private DefaultCustomNavigationElementProvider customNavigationElementProvider = new DefaultCustomNavigationElementProvider();
    private ComponentProvider<SectionNavigationElement> sectionElementProvider = new DefaultSectionElementComponentProvider();
    private ComponentProvider<SubmenuNavigationElement> submenuNavigationElementProvider = new DefaultSubmenuNavigationElementProvider();
    private NavigationElementInfoProvider navigationElementInfoProvider = null;
    private List<AbstractNavigationElement> navigationFooterElements = new ArrayList<>();
    private List<AbstractNavigationElement> navigationHeaderElements = new ArrayList<>();
    private List<NavigatorNavigationElement> navigatorNavigationElements = new ArrayList<>();
    private Component appBarIconComponent;
    private ViewNameInterceptor interceptor = null;

    private AppLayoutBuilder() {
    }

    public static AppLayoutBuilder get() {
        return new AppLayoutBuilder();
    }

    public AppLayoutBuilder withBehaviour(Behaviour variant) {
        this.variant = variant;
        return this;
    }

    /**
     * This will cause parameters set with "withBehaviour(...)" to be ignored
     *
     * @param variant
     * @return
     */
    public AppLayoutBuilder withCustomVariant(AbstractLeftAppLayout variant) {
        this.instance = variant;
        return this;
    }

    public AppLayoutBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public AppLayoutBuilder withNavigatorProducer(NavigatorProducer navigator) {
        this.requiresNavigatior = true;
        this.navigatorProducer = navigator;
        return this;
    }

    public AppLayoutBuilder withNavigationElementInfoProvider(NavigationElementInfoProvider provider) {
        this.requiresNavigatior = true;
        this.navigationElementInfoProvider = provider;
        return this;
    }

    public AppLayoutBuilder withViewNameInterceptor(ViewNameInterceptor interceptor) {
        this.requiresNavigatior = true;
        this.interceptor = interceptor;
        return this;
    }

    public AppLayoutBuilder withDesign(AppBarDesign design) {
        this.design = design;
        return this;
    }

    public AppLayoutBuilder withNavigatorConsumer(NavigatorConsumer consumer) {
        this.requiresNavigatior = true;
        this.navigatorConsumer = consumer;
        return this;
    }


    public AppLayoutBuilder withDefaultNavigationView(View element) {
        requiresNavigatior = true;
        defaultNavigationElement = new NavigatorNavigationElement("", null, element);
        return this;
    }

    public AppLayoutBuilder withDefaultNavigationView(Class<? extends View> element) {
        requiresNavigatior = true;
        defaultNavigationElement = new NavigatorNavigationElement("", null, element);
        return this;
    }

    public AppLayoutBuilder withNavigationElementProvider(ComponentProvider<NavigatorNavigationElement> provider) {
        this.navigationElementProvider = provider;
        return this;
    }

    public AppLayoutBuilder withSectionElementProvider(ComponentProvider<SectionNavigationElement> provider) {
        this.sectionElementProvider = provider;
        return this;
    }

    public AppLayoutBuilder withSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement> provider) {
        this.submenuNavigationElementProvider = provider;
        return this;
    }


    /**
     * If you use this you will need to do the "navigator part" manually, this will simply add a component to the menu
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder add(Component element) {
        return add(element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Component element, Position position) {
        addToPosition(new CustomNavigationElement(element), position);
        return this;
    }

    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener) {
        return addClickable(caption, icon, listener, Position.DEFAULT);
    }

    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener, Position position) {
        addToPosition(new CustomNavigatorNavigationElement(caption, icon, listener), position);
        return this;
    }

    public AppLayoutBuilder add(Component... element) {
        this.navigationElements.addAll(Arrays.stream(element).map(component -> new CustomNavigationElement(component)).collect(Collectors.toList()));
        return this;
    }

    public AppLayoutBuilder addSection(String value) {
        this.navigationElements.add(new SectionNavigationElement(value));
        return this;
    }

    public AppLayoutBuilder addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element) {
        return add(caption, icon, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        return add(caption, icon, badgeHolder, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Resource icon, View element) {
        return add(caption, icon, element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(String caption, Class<? extends View> element) {
        return add(caption, null, element);
    }

    public AppLayoutBuilder add(String caption, View element) {
        return add(caption, null, element);
    }

    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element, Position position) {
        return add(caption, icon, null, element, position);
    }

    public AppLayoutBuilder add(Class<? extends View> className) {
        return add(className, null, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Class<? extends View> className, Position position) {
        return add(className, null, position);
    }

    public AppLayoutBuilder add(Class<? extends View> className, Resource icon) {
        return add(className, icon, Position.DEFAULT);
    }

    public AppLayoutBuilder add(Class<? extends View> className, Resource icon, Position position) {
        return add(new NavigatorNavigationElement(className, icon), position);
    }

    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Position position) {
        requiresNavigatior = true;
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return this;
    }

    public AppLayoutBuilder add(String caption, Resource icon, View element, Position position) {
        requiresNavigatior = true;
        addToPosition(new NavigatorNavigationElement(caption, icon, element), position);
        return this;
    }

    public AppLayoutBuilder add(AbstractNavigationElement element) {
        return add(element, Position.DEFAULT);
    }

    public AppLayoutBuilder add(AbstractNavigationElement element, Position position) {
        if (element instanceof NavigatorNavigationElement) {
            requiresNavigatior = true;
        } else if (element instanceof SubmenuNavigationElement && ((SubmenuNavigationElement) element).requiresNavigator()) {
            requiresNavigatior = ((SubmenuNavigationElement) element).requiresNavigator();
        }
        addToPosition(element, position);
        return this;
    }

    private void addToPosition(AbstractNavigationElement element, Position position) {
        switch (position) {
            case HEADER:
                this.navigationHeaderElements.add(element);
                break;
            case DEFAULT:
                this.navigationElements.add(element);
                break;
            case FOOTER:
                this.navigationFooterElements.add(element);
                break;
        }
    }

    public AppLayout build() {
        if (instance == null) {
            if (!UI.getCurrent().getPage().getWebBrowser().isIE()) {
                instance = variant.getInstance();
            } else {
                instance = new LeftFallBack(variant);
            }
            AppLayoutSessionHelper.setActiveVariant(variant);
        }
        instance.setTitle(title);
        if (requiresNavigatior) {
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
        addComponents(navigationHeaderElements, instance::addNavigationHeaderElement);
        addComponents(navigationElements, instance::addNavigationElement);
        addComponents(navigationFooterElements, instance::addNavigationFooterElement);
        appBarElements.forEach(instance::addAppBarElement);
        instance.setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        return instance;
    }

    Optional<NavigatorNavigationElement> findNextNavigationElement(String viewName) {
        if (viewName.equals("")) {
            return navigatorNavigationElements.stream()
                    .filter(element -> element.getViewClassName().equals(defaultNavigationElement.getViewClassName()) || element.getViewName().equals(""))
                    .findFirst();
        }
        return navigatorNavigationElements.stream()
                .filter(element -> element instanceof NavigatorNavigationElement)
                .filter(element -> element.getViewName().equals(viewName))
                .findFirst();
    }

    public void addComponents(List<AbstractNavigationElement> elements, ComponentConsumer consumer) {
        elements.forEach(element -> {
            setProvider(element);
            consumer.accept(element.getComponent());
        });
    }

    public void setProvider(AbstractNavigationElement element) {
        if (element instanceof NavigatorNavigationElement) {
            NavigatorNavigationElement nelement = (NavigatorNavigationElement) element;
            nelement.setNavigationElementInfoProvider(navigationElementInfoProvider);
            nelement.setProvider(navigationElementProvider);
            if (nelement.getViewClassName() == defaultNavigationElement.getViewClassName()) {
                AppLayoutSessionHelper.updateActiveElementSessionData(nelement);
            }
            navigatorNavigationElements.add(nelement);
            nelement.setViewNameInterceptor(interceptor);
            nelement.addViewToNavigator(navigator);
        } else if (element instanceof CustomNavigatorNavigationElement) {
            CustomNavigatorNavigationElement cnelement = (CustomNavigatorNavigationElement) element;
            cnelement.setProvider(customNavigationElementProvider);
        } else if (element instanceof SubmenuNavigationElement) {
            SubmenuNavigationElement selement = (SubmenuNavigationElement) element;
            selement.setProvider(submenuNavigationElementProvider);
            selement.getSubmenuElements().forEach(element1 -> setProvider(element1));
        } else if (element instanceof SectionNavigationElement) {
            SectionNavigationElement selement = (SectionNavigationElement) element;
            selement.setProvider(sectionElementProvider);
        }
    }

    public AppLayoutBuilder addToAppBar(Component element) {
        this.appBarElements.add(element);
        return this;
    }

    public AppLayoutBuilder addToAppBar(Component... element) {
        this.appBarElements.addAll(Arrays.asList(element));
        return this;
    }

    public AppLayoutBuilder withAppBarIconComponent(Component resourceButton) {
        this.appBarIconComponent = resourceButton;
        return this;
    }

    public enum Position {
        HEADER, DEFAULT, FOOTER
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
    interface ComponentConsumer extends Consumer<Component> {
    }

}
