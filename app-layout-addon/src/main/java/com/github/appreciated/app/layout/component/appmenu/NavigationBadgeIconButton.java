package com.github.appreciated.app.layout.component.appmenu;

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

    public NavigationBadgeIconButton(String name, Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        this(name, icon);
        if (listener != null) {
            setClickListener(appMenuIconItemClickEvent -> listener.onComponentEvent(null));
        }
    }

    public NavigationBadgeIconButton(String name, Icon icon) {
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
