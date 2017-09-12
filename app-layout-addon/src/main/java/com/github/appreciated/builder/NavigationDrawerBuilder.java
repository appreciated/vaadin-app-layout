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
    List<java.util.Map.Entry<String, Object>> navigationViews = new ArrayList<>();
    List<Component> appBarElements = new ArrayList<>();
    private boolean hasNavigatior = false;
    private Navigator navigator;
    private NavigationViewComponentProvider viewComponentProvider = new DefaultViewComponentProvider();
    private String title;
    private AbstractNavigationDrawer instance = null;
    private Object defaultNavigationView;

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

    public NavigationDrawerBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NavigationDrawerBuilder withNavigator() {
        this.hasNavigatior = true;
        return this;
    }

    public NavigationDrawerBuilder withNavigationViewComponentProvider(NavigationViewComponentProvider provider) {
        this.viewComponentProvider = provider;
        return this;
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

    public NavigationDrawerBuilder withDefaultNavigationView(View element) {
        if (hasNavigatior == false) {
            hasNavigatior = true;
        }
        defaultNavigationView = element;
        return this;
    }

    public NavigationDrawerBuilder withDefaultNavigationView(Class<? extends View> element) {
        if (hasNavigatior == false) {
            hasNavigatior = true;
        }
        defaultNavigationView = element;
        return this;
    }

    public NavigationDrawerBuilder withNavigationView(String view, View element) {
        if (hasNavigatior == false) {
            hasNavigatior = true;
        }
        this.navigationViews.add(new AbstractMap.SimpleEntry<>(view, element));
        return this;
    }

    public NavigationDrawerBuilder withNavigationView(String view, Class<? extends View> element) {
        if (hasNavigatior == false) {
            hasNavigatior = true;
        }
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
            if (defaultNavigationView == null) {
                defaultNavigationView = navigationViews.get(0).getValue();
            }
            addViewToNavigator("", defaultNavigationView);
            navigationViews.forEach(view -> {
                addViewToNavigator(view.getKey(), view.getValue());
                addViewComponent(view.getKey(), view.getValue());
            });
        }
        appBarElements.forEach(instance::addAppBarElement);
        return instance;
    }

    private void addViewToNavigator(String viewName, Object o) {
        if (o instanceof Class) {
            addViewToNavigator(viewName, (Class<? extends View>) o);
        } else if (o instanceof View) {
            addViewToNavigator(viewName, (View) o);
        }
    }

    private void addViewComponent(String viewName, Object o) {
        if (o instanceof Class) {
            addViewComponent(viewName, (Class<? extends View>) o);
        } else if (o instanceof View) {
            addViewComponent(viewName, (View) o);
        }
    }

    private void addViewComponent(String viewName, Class<? extends View> view) {
        instance.addNavigationElement(viewComponentProvider.getComponentForViewClass(viewName, view));
    }

    private void addViewComponent(String viewName, View view) {
        instance.addNavigationElement(viewComponentProvider.getComponentForView(viewName, view));
    }

    private void addViewToNavigator(String viewName, Class<? extends View> view) {
        navigator.addView(viewName, view);
    }

    private void addViewToNavigator(String viewName, View view) {
        navigator.addView(viewName, view);
    }
}
