package com.github.appreciated.app.layout.builder.elements;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class NavigatorNavigationElement extends AbstractNavigationElement<NavigatorNavigationElement> {
    private final String name;
    private View view;
    private Resource icon;
    private Class<? extends View> className;

    public NavigatorNavigationElement(String name, Resource icon, Class<? extends View> className) {
        this.name = name;
        this.icon = icon;
        this.className = className;
    }

    public NavigatorNavigationElement(String name, Resource icon, View view) {
        this.name = name;
        this.icon = icon;
        this.view = view;
    }

    public void addViewToNavigator(Navigator navigator) {
        if (view != null) {
            navigator.addView(name, view);
        } else if (className != null) {
            navigator.addView(name, className);
        }
    }

    public void setAsDefaultView(Navigator navigator) {
        if (view != null) {
            navigator.addView("", view);
        } else if (className != null) {
            navigator.addView("", className);
        }
    }

    public String getName() {
        return name;
    }

    public Resource getIcon() {
        return icon;
    }

    @Override
    NavigatorNavigationElement getInfo() {
        return this;
    }
}
