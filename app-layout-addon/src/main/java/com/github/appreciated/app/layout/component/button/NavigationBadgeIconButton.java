package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;

public class NavigationBadgeIconButton extends HorizontalLayout implements NavigationElementComponent {

    private final Label badge;
    private final Component toggle;

    public NavigationBadgeIconButton(String name, Icon icon, DefaultBadgeHolder status) {
        this(name, icon, status, null);
    }

    public NavigationBadgeIconButton(String name, Icon icon, DefaultBadgeHolder status, ComponentEventListener<ClickEvent<Button>> listener) {
        setWidth("100%");
        if (icon != null) {
            toggle = new PaperDrawerIconItem(name, icon.getElement().getAttribute("icon"));
        } else {
            toggle = new PaperDrawerItem(name);
        }
        toggle.getElement().getStyle().set("height", "48px").set("width", "100%");//.set("position", "absolute");
        add(toggle);

        badge = new Label();
        if (status != null) {
            status.addListener((newStatus) -> setStatus(newStatus));
        }
        setStatus(status);
        badge.getClassNames().add(APP_LAYOUT_MENU_BUTTON_BADGE);
        if (listener != null) {
            if (toggle instanceof PaperDrawerIconItem) {
                ((PaperDrawerIconItem) toggle).setClickListener(paperDrawerIconItemClickEvent -> listener.onComponentEvent(null));
            } else if (toggle instanceof PaperDrawerItem) {
                ((PaperDrawerItem) toggle).setClickListener(paperDrawerIconItemClickEvent -> listener.onComponentEvent(null));
            }
        }
        add(badge);
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
        if (toggle instanceof PaperDrawerIconItem) {
            ((PaperDrawerIconItem) toggle).setIcon(resource);
        }
    }

    @Override
    public void setNavigationCaption(String string) {
        if (toggle instanceof PaperDrawerIconItem) {
            ((PaperDrawerIconItem) toggle).setTitle(string);
        } else if (toggle instanceof PaperDrawerItem) {
            ((PaperDrawerIconItem) toggle).setTitle(string);
        }
    }

    public void setClickListener(ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener) {
        if (toggle instanceof PaperDrawerIconItem) {
            ((PaperDrawerIconItem) toggle).setClickListener(paperDrawerIconItemClickEvent -> listener.onComponentEvent(null));
        } else if (toggle instanceof PaperDrawerItem) {
            ((PaperDrawerItem) toggle).setClickListener(paperDrawerIconItemClickEvent -> listener.onComponentEvent(null));
        }
    }
}
