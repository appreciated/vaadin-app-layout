package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class IconButton extends Div {

    private static int idCounter = 0;

    private final Button button;

    public IconButton(Icon icon) {
        this(icon, null);
    }

    public IconButton(Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        setId("menu-btn-" + idCounter++);
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        button = new Button(icon);
        button.getElement().getStyle()
                .set("width", "100%")
                .set("height", "100%");
        button.getElement().setAttribute("id", "button");
        add(button);
        if (listener != null) {
            button.addClickListener(listener);
        }
    }

    public void setIcon(Icon icon) {
        button.setIcon(icon);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public Button getButton() {
        return button;
    }
}
