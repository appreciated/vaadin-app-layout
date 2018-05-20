package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;

/**
 *
 */
public class NavigationBadgeButton extends PaperDrawerIconItem implements NavigationElementComponent {

    private final Label badge;

    public NavigationBadgeButton(String name, Icon icon, DefaultBadgeHolder status) {
        super(name, "");
        badge = new Label();
        if (status != null) {
            status.addListener((newStatus) -> setStatus(newStatus));
        }
        setStatus(status);
        badge.getClassNames().add(APP_LAYOUT_MENU_BUTTON_BADGE);
/*
        add(button);
        setFlexGrow(1.0, button);
        add(badge);*/
    }

    public NavigationBadgeButton(String name, Icon icon, DefaultBadgeHolder status, ComponentEventListener<ClickEvent<Button>> listener) {
        this(name, icon, status);
        setClickListener(paperDrawerIconItemClickEvent -> listener.onComponentEvent(null));
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
    public void setNavigationIcon(String resource) {
        setIcon(resource);
    }

    @Override
    public void setNavigationCaption(String string) {
        setTitle(string);
    }
}
