package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;
import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT;


public class NavigationBadgeButton extends HorizontalFlexBoxLayout implements NavigationElementComponent {

    private final NavigationButton button;
    private final Label badge;

    public NavigationBadgeButton(String name, Resource icon, DefaultBadgeHolder status) {
        addStyleName(APP_LAYOUT_MENU_ELEMENT);
        setHeight(48, Unit.PIXELS);
        setWidth(100, Sizeable.Unit.PERCENTAGE);
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        if (status != null) {
            button.addStyleName("app-layout-menu-badge-button");
        }
        badge = new Label();
        if (status != null) {
            status.addListener((newStatus) -> setStatus(newStatus));
        }
        setStatus(status);
        badge.addStyleName(APP_LAYOUT_MENU_BUTTON_BADGE);
        addComponent(button);
        grow(button);
        addComponent(badge);
    }

    public NavigationBadgeButton(String name, Resource icon, DefaultBadgeHolder status, Button.ClickListener listener) {
        this(name, icon, status);
        button.addClickListener(listener);
    }

    public NavigationButton getButton() {
        return button;
    }

    private void setStatus(DefaultBadgeHolder status) {
        if (status != null) {
            int unreadNotifications = status.getCount();
            if (unreadNotifications > 0) {
                badge.setVisible(true);
                if (unreadNotifications < 10) {
                    badge.setValue(String.valueOf(unreadNotifications));
                } else {
                    badge.setValue("9+");
                }
            } else {
                badge.setVisible(false);
            }
        } else {
            badge.setVisible(false);
        }
    }

    @Override
    public void setNavigationIcon(Resource resource) {
        setIcon(resource);
    }

    @Override
    public void setNavigationCaption(String string) {
        setCaption(string);
    }
}
