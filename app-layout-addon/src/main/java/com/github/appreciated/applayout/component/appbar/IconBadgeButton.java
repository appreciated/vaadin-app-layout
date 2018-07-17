package com.github.appreciated.applayout.component.appbar;

import com.github.appreciated.applayout.webcomponents.paperbadge.PaperBadge;
import com.github.appreciated.applayout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class IconBadgeButton extends Div {

    static int idCounter = 0;

    private final PaperIconButton button;
    private final PaperBadge badge;

    public IconBadgeButton(Icon icon) {
        this(icon, null);
    }

    public IconBadgeButton(Icon icon, ComponentEventListener<ClickEvent<PaperIconButton>> listener) {
        setId("menu-btn-" + idCounter++);
        setWidth("48px");
        setHeight("48px");
        button = new PaperIconButton(icon.getElement().getAttribute("icon"));
        button.getElement().getStyle()
                .set("width", "100%")
                .set("height", "100%");
        button.getElement().setAttribute("id", "button");
        badge = new PaperBadge(this, "0");
        badge.getElement().getStyle()
                .set("margin-top", "6px")
                .set("margin-left", "-6px");

        add(button);
        add(badge);
        if (listener != null) {
            button.setClickListener(listener);
        }
    }

    public void setBadgeCaption(String caption) {
        badge.setText(caption);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }

    public PaperIconButton getButton() {
        return button;
    }

    public HasText getBadge() {
        return badge;
    }
}


