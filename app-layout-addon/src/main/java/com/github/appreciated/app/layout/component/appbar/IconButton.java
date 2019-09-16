package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class IconButton extends Button {

    public IconButton(Component icon) {
        super(icon);
        init();
    }

    public IconButton(Component icon, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(icon, clickListener);
        init();
    }

    public IconButton(Icon icon) {
        super(icon);
        init();
    }

    public IconButton(Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        super(icon, listener);
        init();
    }

    private void init() {
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
    }

}
