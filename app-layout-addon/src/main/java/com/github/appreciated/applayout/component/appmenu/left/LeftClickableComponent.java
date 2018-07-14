package com.github.appreciated.applayout.component.appmenu.left;

import com.github.appreciated.applayout.component.appmenu.NavigationBadgeIconButton;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for {@link LeftClickableComponent}.
 */
public class LeftClickableComponent extends NavigationBadgeIconButton {
    private final String name;
    private Icon icon;
    private ComponentEventListener<ClickEvent<?>> listener;

    public LeftClickableComponent(String name, Icon icon, ComponentEventListener<ClickEvent<?>> listener) {
        super(name, icon, null);
        this.name = name;
        this.icon = icon;
        this.listener = listener;

        setClickListener(appMenuIconItemClickEvent -> getListener().onComponentEvent(null));
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    public ComponentEventListener<ClickEvent<?>> getListener() {
        return listener;
    }
}
