package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class IconButton extends Button {

    public IconButton(Icon icon) {
        this(icon, null);
    }

    public IconButton(Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        setIcon(icon);
        if (listener != null) {
            addClickListener(listener);
        }
        getElement().getStyle()
                .set("width", "100%")
                .set("height", "100%");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

}
