package com.github.appreciated.app.layout.component.button;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;

/**
 * The default {@link Component} to be used to display menu entries
 */
public class NavigationButton extends Button {
    public NavigationButton(String name, Component icon) {
        super(name);
        setIcon(icon);
        /*addStyleName(ValoTheme.BUTTON_BORDERLESS);
        addStyleName(Styles.APP_LAYOUT_MENU_BUTTON);
        addStyleName(APP_LAYOUT_MENU_BUTTON_NO_BORDER_READIUS); // for material theme only*/
        setHeight("48px");
        setWidth("100%");
    }

    public NavigationButton(String name, Component icon, ComponentEventListener<ClickEvent<Button>> listener) {
        this(name, icon);
        addClickListener(listener);
    }
}
