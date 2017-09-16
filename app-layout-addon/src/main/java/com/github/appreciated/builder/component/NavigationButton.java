package com.github.appreciated.builder.component;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;


public class NavigationButton extends Button {
    public NavigationButton(String name, Resource icon) {
        com.vaadin.ui.Button button = new com.vaadin.ui.Button(name);
        button.setIcon(icon);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addStyleName("app-layout-menu-button");
        button.addStyleName("no-border-radius"); // for material theme only
        button.setWidth(100, Sizeable.Unit.PERCENTAGE);
    }
}
