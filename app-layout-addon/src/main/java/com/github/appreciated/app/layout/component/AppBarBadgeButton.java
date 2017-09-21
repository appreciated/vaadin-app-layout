package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.entities.BadgeStatus;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.APP_BAR_BADGE;


public class AppBarBadgeButton extends AbsoluteLayout {

    private final AppBarButton button;
    private final Label badge;

    public AppBarBadgeButton(Resource icon, BadgeStatus status) {
        setWidth("64px");
        setHeight("64px");
        button = new AppBarButton(icon);
        button.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.BUTTON_ICON_ONLY);
        button.setSizeFull();
        badge = new Label();
        if (status != null) {
            status.addStatusListener(newStatus -> setStatus(newStatus));
        }
        setStatus(status);
        badge.addStyleName(APP_BAR_BADGE);
        addComponent(button);
        addComponent(badge, "right: 0px;");
    }

    public AppBarBadgeButton(Resource icon, BadgeStatus status, Button.ClickListener listener) {
        this(icon, status);
        button.addClickListener(listener);
    }

    public AppBarButton getButton() {
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


