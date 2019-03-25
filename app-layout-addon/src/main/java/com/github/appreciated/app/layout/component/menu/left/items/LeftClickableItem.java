package com.github.appreciated.app.layout.component.menu.left.items;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for {@link LeftClickableItem}.
 */
public class LeftClickableItem extends LeftBadgeIconItem {
    private final String name;
    private Icon icon;
    private ComponentEventListener<ClickEvent<?>> listener;

    public LeftClickableItem(String name, Icon icon, ComponentEventListener<ClickEvent<?>> listener) {
        super(name, icon, null);
        this.name = name;
        this.icon = icon;
        this.listener = listener;
        setHighlightCondition((routerLink, event) -> false);
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
