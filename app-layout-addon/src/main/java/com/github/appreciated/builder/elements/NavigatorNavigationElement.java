package com.github.appreciated.builder.elements;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class NavigatorNavigationElement extends AbstractNavigationElement {
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

    public Component getComponent() {
        Button button = new Button(name);
        button.setIcon(icon);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addStyleName("app-layout-menu-button");
        button.addStyleName("no-border-radius"); // for material theme only
        button.setWidth(100, Sizeable.Unit.PERCENTAGE);
        button.addClickListener(clickEvent -> UI.getCurrent().getNavigator().navigateTo(name));
        return button;
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

}
