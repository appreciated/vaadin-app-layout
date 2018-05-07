package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.*;

/**
 *
 */
public class NavigationBadgeButton extends HorizontalLayout implements NavigationElementComponent {

    private final NavigationButton button;
    private final Label badge;

    public NavigationBadgeButton(String name, Icon icon, DefaultBadgeHolder status) {
        getElement().getClassList().add(APP_LAYOUT_MENU_ELEMENT);
        setHeight("48px");
        setWidth("100%");
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        if (status != null) {
            button.getClassNames().add(APP_LAYOUT_MENU_BADGE_BUTTON);
        }
        badge = new Label();
        if (status != null) {
            status.addListener((newStatus) -> setStatus(newStatus));
        }
        setStatus(status);
        badge.getClassNames().add(APP_LAYOUT_MENU_BUTTON_BADGE);
        add(button);
        setFlexGrow(1.0, button);
        add(badge);
    }

    public NavigationBadgeButton(String name, Icon icon, DefaultBadgeHolder status, ComponentEventListener<ClickEvent<Button>> listener) {
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
                    badge.setText(String.valueOf(unreadNotifications));
                } else {
                    badge.setText("9+");
                }
            } else {
                badge.setVisible(false);
            }
        } else {
            badge.setVisible(false);
        }
    }

    @Override
    public void setNavigationIcon(Icon resource) {
        this.button.setIcon(resource);
    }

    @Override
    public void setNavigationCaption(String string) {
        this.button.setText(string);
    }
}
