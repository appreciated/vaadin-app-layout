package com.github.appreciated.app.layout.component.menu.left.items;

import com.github.appreciated.app.layout.component.menu.MenuBadgeComponent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

public class LeftBadgeIconItem extends LeftIconItem {

    private static int idCounter = 0;

    private final MenuBadgeComponent badge;

    public LeftBadgeIconItem(String caption, Component icon, ComponentEventListener<ClickEvent<Button>> listener) {
        this(caption, icon);
        if (listener != null) {
            setClickListener(appMenuIconItemClickEvent -> listener.onComponentEvent(null));
        }
    }

    public LeftBadgeIconItem(String name, Component icon) {
        super(name, icon);
        setId("menu-btn-" + idCounter++);
        badge = new MenuBadgeComponent();
        badge.setVisible(false);
        add(badge);
    }


    public HasText getBadge() {
        return badge;
    }


}
