package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;


public class NavigationButton extends Button {
    public NavigationButton(String name, Resource icon) {
        super(name);
        setIcon(icon);
        addStyleName(ValoTheme.BUTTON_BORDERLESS);
        addStyleName("app-layout-menu-button");
        addStyleName("no-border-radius"); // for material theme only
        setWidth(100, Sizeable.Unit.PERCENTAGE);
    }
}
