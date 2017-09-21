package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.entities.BadgeStatus;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;
import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT;


public class NavigationBadgeButton extends AbsoluteLayout {

    private final NavigationButton button;
    private final Label badge;

    public NavigationBadgeButton(String name, Resource icon, BadgeStatus status) {
        addStyleName(APP_LAYOUT_MENU_ELEMENT);
        setHeight(48, Unit.PIXELS);
        setWidth(100, Sizeable.Unit.PERCENTAGE);
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        badge = new Label();
        if (status != null) {
            status.addStatusListener(newStatus -> setStatus(newStatus));
        }
        setStatus(status);

        badge.addStyleName(APP_LAYOUT_MENU_BUTTON_BADGE);
        ComponentPosition badgePosition = new ComponentPosition();

        addComponent(button);
        addComponent(badge, "right: 0px;");
    }

    public NavigationBadgeButton(String name, Resource icon, BadgeStatus status, Button.ClickListener listener) {
        this(name, icon, status);
        button.addClickListener(listener);
    }

    public NavigationButton getButton() {
        return button;
    }

    private void setStatus(BadgeStatus status) {
        if (status != null) {
            badge.setVisible(true);
            badge.setValue(status.getStatus());
        } else {
            badge.setVisible(false);
        }
    }
}
