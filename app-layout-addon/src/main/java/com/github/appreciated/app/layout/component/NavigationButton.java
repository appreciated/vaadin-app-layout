package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.Styles;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_NO_BORDER_READIUS;

/**
 * The default component to be used to display menu entries
 */
public class NavigationButton extends Button {
    public NavigationButton(String name, Resource icon) {
        super(name);
        setIcon(icon);
        addStyleName(ValoTheme.BUTTON_BORDERLESS);
        addStyleName(Styles.APP_LAYOUT_MENU_BUTTON);
        addStyleName(APP_LAYOUT_MENU_BUTTON_NO_BORDER_READIUS); // for material theme only
        setHeight(48, Unit.PIXELS);
        setWidth(100, Sizeable.Unit.PERCENTAGE);
    }

    public NavigationButton(String name, Resource icon, Button.ClickListener listener) {
        this(name, icon);
        addClickListener(listener);
    }
}
