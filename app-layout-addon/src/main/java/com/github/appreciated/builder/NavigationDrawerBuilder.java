package com.github.appreciated.builder;

import com.github.appreciated.layout.drawer.AbstractNavigationDrawer;
import com.github.appreciated.provider.DefaultViewComponentProvider;
import com.github.appreciated.provider.NavigationViewComponentProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerBuilder {

    DrawerVariant variant = DrawerVariant.LEFT;
    List<Component> navigationElements = new ArrayList<>();
    List<java.util.Map.Entry<String, View>> navigationViews = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private boolean hasNavigatior = false;
    private Navigator navigator;
    private NavigationViewComponentProvider viewComponentProvider = new DefaultViewComponentProvider();
    private String title;
    private AbstractNavigationDrawer instance = null;

    private NavigationDrawerBuilder() {
    }

    public static NavigationDrawerBuilder get() {
        return new NavigationDrawerBuilder();
    }

    public NavigationDrawerBuilder withVariant(DrawerVariant variant) {
        this.variant = variant;
        return this;
    }

    public NavigationDrawerBuilder withCustomVariant(AbstractNavigationDrawer variant) {
        this.instance = variant;
        return this;
    }

    public void withTitle(String title) {
        this.title = title;
    }

    public void withNavigator() {
        this.hasNavigatior = true;
    }

    public void withNavigationViewComponentProvider(NavigationViewComponentProvider provider) {
        this.viewComponentProvider = provider;
    }


    /**
     * If you use this you will need to do the "navigator part" manually, this will simply add a component to the menu
     *
     * @param element
     * @return
     */
    public NavigationDrawerBuilder withNavigationElement(Component element) {
        this.navigationElements.add(element);
        return this;
    }

    public NavigationDrawerBuilder withNavigationView(String view, View element) {
        this.navigationViews.add(new AbstractMap.SimpleEntry<>(view, element));
        return this;
    }

    public NavigationDrawerBuilder withAppBarElement(Component element) {
        this.appBarElements.add(element);
        return this;
    }

    public AbstractNavigationDrawer build() {
        if (instance == null) {
            instance = variant.getInstance();
        }
        instance.setTitle(title);
        navigationElements.forEach(instance::addNavigationElement);
        if (hasNavigatior) {
            navigator = new Navigator(UI.getCurrent(), instance.getContentHolder());
            navigationViews.forEach(view -> {
                navigator.addView(view.getKey(), view.getValue());
                instance.addNavigationElement(viewComponentProvider.getComponentForView(view.getKey(), view.getValue()));
            });
        }
        appBarElements.forEach(instance::addAppBarElement);
        return instance;
    }
}
