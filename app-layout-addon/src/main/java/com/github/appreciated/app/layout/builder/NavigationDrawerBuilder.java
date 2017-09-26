package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.providers.DefaultCustomNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.DefaultNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.DefaultSectionElementComponentProvider;
import com.github.appreciated.app.layout.drawer.AbstractNavigationDrawer;
import com.github.appreciated.app.layout.session.NavigationElementHelper;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NavigationDrawerBuilder {

    private NavigationConsumer navigatorConsumer;
    private NavigatorProducer navigatorProducer = components -> new Navigator(UI.getCurrent(), components);

    DrawerVariant variant = DrawerVariant.LEFT;
    List<AbstractNavigationElement> navigationElements = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private boolean requiresNavigatior = false;
    private AppBarDesign design = AppBarDesign.DEFAULT;
    private Navigator navigator;
    private String title;
    private AbstractNavigationDrawer instance = null;
    private NavigatorNavigationElement defaultNavigationElement;
    private ComponentProvider<NavigatorNavigationElement> navigationElementProvider = new DefaultNavigationBadgeElementComponentProvider();
    private DefaultCustomNavigationElementProvider customNavigationElementProvider = new DefaultCustomNavigationElementProvider();
    private ComponentProvider<SectionNavigationElement> sectionElementProvider = new DefaultSectionElementComponentProvider();
    private List<AbstractNavigationElement> navigationFooterElements = new ArrayList<>();
    private List<AbstractNavigationElement> navigationHeaderElements = new ArrayList<>();
    private Component appBarIconComponent;

    private NavigationDrawerBuilder() {
    }

    /**
     * If you use this you will need to do the "navigator part" manually, this will simply add a component to the menu
     *
     * @param element
     * @return
     */
    public NavigationDrawerBuilder withNavigationElement(Component element) {
        return withNavigationElement(element, Position.DEFAULT);
    }

    public NavigationDrawerBuilder withNavigationElement(Component element, Position position) {
        addNavigationElementToPosition(new CustomNavigationElement(element), position);
        return this;
    }

    public static NavigationDrawerBuilder get() {
        return new NavigationDrawerBuilder();
    }

    public NavigationDrawerBuilder withVariant(DrawerVariant variant) {
        this.variant = variant;
        return this;
    }

    /**
     * This will cause parameters set with "withVariant(...)" to be ignored
     *
     * @param variant
     * @return
     */
    public NavigationDrawerBuilder withCustomVariant(AbstractNavigationDrawer variant) {
        this.instance = variant;
        return this;
    }

    public NavigationDrawerBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NavigationDrawerBuilder withNavigatorProducer(NavigatorProducer navigator) {
        this.requiresNavigatior = true;
        this.navigatorProducer = navigator;
        return this;
    }

    public NavigationDrawerBuilder withDesign(AppBarDesign design) {
        this.design = design;
        return this;
    }

    public NavigationDrawerBuilder withNavigatorConsumer(NavigationConsumer consumer) {
        this.requiresNavigatior = true;
        this.navigatorConsumer = consumer;
        return this;
    }

    public NavigationDrawerBuilder withClickableElement(String caption, Resource icon, Button.ClickListener listener) {
        return withClickableElement(caption, icon, listener, Position.DEFAULT);
    }

    public NavigationDrawerBuilder withClickableElement(String caption, Resource icon, Button.ClickListener listener, Position position) {
        addNavigationElementToPosition(new CustomNavigatorNavigationElement(caption, icon, listener), position);
        return this;
    }

    public NavigationDrawerBuilder withNavigationElements(Component... element) {
        this.navigationElements.addAll(Arrays.stream(element).map(component -> new CustomNavigationElement(component)).collect(Collectors.toList()));
        return this;
    }

    public NavigationDrawerBuilder withDefaultNavigationView(View element) {
        requiresNavigatior = true;
        defaultNavigationElement = new NavigatorNavigationElement("", null, element);
        return this;
    }

    public NavigationDrawerBuilder withDefaultNavigationView(Class<? extends View> element) {
        requiresNavigatior = true;
        defaultNavigationElement = new NavigatorNavigationElement("", null, element);
        return this;
    }

    public NavigationDrawerBuilder withSection(String value) {
        this.navigationElements.add(new SectionNavigationElement(value));
        return this;
    }

    public NavigationDrawerBuilder withClickableElement(String caption, Button.ClickListener listener) {
        return withClickableElement(caption, null, listener);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, Class<? extends View> element) {
        return withNavigationElement(caption, icon, element, Position.DEFAULT);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, NotificationHolder provider, Class<? extends View> element) {
        return withNavigationElement(caption, icon, provider, element, Position.DEFAULT);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, View element) {
        return withNavigationElement(caption, icon, element, Position.DEFAULT);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Class<? extends View> element) {
        return withNavigationElement(caption, null, element);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, View element) {
        return withNavigationElement(caption, null, element);
    }

    public NavigationDrawerBuilder withNavigationElementProvider(ComponentProvider<NavigatorNavigationElement> provider) {
        this.navigationElementProvider = provider;
        return this;
    }

    public NavigationDrawerBuilder withSectionElementProvider(ComponentProvider<SectionNavigationElement> provider) {
        this.sectionElementProvider = provider;
        return this;
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, Class<? extends View> element, Position position) {
        return withNavigationElement(caption, icon, null, element, position);
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, NotificationHolder provider, Class<? extends View> element, Position position) {
        requiresNavigatior = true;
        addNavigationElementToPosition(new NavigatorNavigationElement(caption, icon, provider, element), position);
        return this;
    }

    public NavigationDrawerBuilder withNavigationElement(String caption, Resource icon, View element, Position position) {
        requiresNavigatior = true;
        addNavigationElementToPosition(new NavigatorNavigationElement(caption, icon, element), position);
        return this;
    }

    private void addNavigationElementToPosition(AbstractNavigationElement element, Position position) {
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

    public AbstractNavigationDrawer build() {
        if (instance == null) {
            instance = variant.getInstance();
        }
        instance.setTitle(title);
        if (requiresNavigatior) {
            navigator = navigatorProducer.apply(instance.getContentHolder());
            navigator.addViewChangeListener(viewChangeEvent -> {
                NavigationElementHelper.removeStyleFromCurrentlyActiveNavigationElement();
                navigationElements.stream()
                        .filter(element -> element instanceof NavigatorNavigationElement)
                        .map(element -> ((NavigatorNavigationElement) element))
                        .filter(element -> element.getName().equals(viewChangeEvent.getViewName()))
                        .findFirst().ifPresent(element -> NavigationElementHelper.setActiveNavigationElement(element));
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

    public void addComponents(List<AbstractNavigationElement> elements, ComponentConsumer consumer) {
        elements.forEach(element -> {
            if (element instanceof NavigatorNavigationElement) {
                NavigatorNavigationElement nelement = (NavigatorNavigationElement) element;
                nelement.setProvider(navigationElementProvider);
                if (nelement.getViewClassName() == defaultNavigationElement.getViewClassName()) {
                    NavigationElementHelper.updateActiveElementSessionData(nelement);
                }
                nelement.addViewToNavigator(navigator);
            } else if (element instanceof CustomNavigatorNavigationElement) {
                CustomNavigatorNavigationElement cnelement = (CustomNavigatorNavigationElement) element;
                cnelement.setProvider(customNavigationElementProvider);
            } else if (element instanceof SectionNavigationElement) {
                SectionNavigationElement selement = (SectionNavigationElement) element;
                selement.setProvider(sectionElementProvider);
            }
            consumer.accept(element.getComponent());
        });
    }

    public NavigationDrawerBuilder withAppBarElement(Component element) {
        this.appBarElements.add(element);
        return this;
    }

    public NavigationDrawerBuilder withAppBarElements(Component... element) {
        this.appBarElements.addAll(Arrays.asList(element));
        return this;
    }

    public NavigationDrawerBuilder withAppBarIconComponent(Component resourceButton) {
        this.appBarIconComponent = resourceButton;
        return this;
    }

    public enum Position {
        HEADER, DEFAULT, FOOTER
    }

    @FunctionalInterface
    public interface NavigationConsumer extends Consumer<Navigator> {
    }

    @FunctionalInterface
    public interface NavigatorProducer extends Function<VerticalLayout, Navigator> {
    }

    @FunctionalInterface
    interface ComponentConsumer extends Consumer<Component> {
    }
}
