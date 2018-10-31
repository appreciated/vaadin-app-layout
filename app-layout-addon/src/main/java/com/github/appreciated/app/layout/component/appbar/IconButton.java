package com.github.appreciated.app.layout.component.appbar;

import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class IconButton extends Div {

    private static int idCounter = 0;

    private final PaperIconButton button;

    public IconButton(Icon icon) {
        this(icon, null);
    }

    public IconButton(Icon icon, ComponentEventListener<ClickEvent<PaperIconButton>> listener) {
        setId("menu-btn-" + idCounter++);
        setWidth("48px");
        setHeight("48px");
        button = new PaperIconButton(icon.getElement().getAttribute("icon"));
        button.getElement().getStyle()
                .set("width", "100%")
                .set("height", "100%");
        button.getElement().setAttribute("id", "button");
        add(button);
        if (listener != null) {
            button.setClickListener(listener);
        }
    }

    public void setIcon(Icon icon) {
        button.setIcon(icon.getElement().getAttribute("icon"));
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public PaperIconButton getButton() {
        return button;
    }
}
