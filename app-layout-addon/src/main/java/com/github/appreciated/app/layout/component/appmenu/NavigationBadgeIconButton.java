package com.github.appreciated.app.layout.component.appmenu;

import com.github.appreciated.app.layout.webcomponents.appmenu.AppMenuIconItem;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

public class NavigationBadgeIconButton extends AppMenuIconItem {

    private static int idCounter = 0;

    private final MenuBadgeComponent badge;

    public NavigationBadgeIconButton() {
        setId("menu-btn-" + idCounter++);
        badge = new MenuBadgeComponent();
        badge.setVisible(false);
        add(badge);
    }

    @Override
    public void setIcon(String icon) {
        super.setIcon(icon);
        getItem().getElement().getStyle().set("white-space", "nowrap");
    }

    public NavigationBadgeIconButton(String name, Icon icon) {
        super(name, icon.getElement().getAttribute("icon"));
        setId("menu-btn-" + idCounter++);
        badge = new MenuBadgeComponent();
        badge.setVisible(false);
        add(badge);
        getItem().getElement().getStyle().set("white-space", "nowrap");
    }

    public NavigationBadgeIconButton(String name, Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        this(name, icon);
        if (listener != null) {
            setClickListener(appMenuIconItemClickEvent -> listener.onComponentEvent(null));
        }
    }

    public void setBadgeCaption(String status) {
        badge.setText(status);
    }

    public HasText getBadge() {
        return badge;
    }

    @Override
    public void setClickListener(ComponentEventListener listener) {
        super.setClickListener(listener);
    }

}
