package com.github.appreciated.app.layout.component.menu.top.item;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for {@link TopClickableItem}.
 */
public class TopClickableItem extends Button {
    private static final long serialVersionUID = 1L;

    private String name;
    private Component icon;
    private ComponentEventListener<ClickEvent<?>> listener;

    public TopClickableItem(String name, Component icon, ComponentEventListener<ClickEvent<?>> listener) {
        super(name, icon);
        this.name = name;
        this.icon = icon;
        this.listener = listener;
        getElement().setAttribute("theme", "tertiary");
        getStyle().set("margin", "unset").set("--app-layout-primary-text-color", "var(--app-layout-bar-font-color)");
        addClickListener(appMenuIconItemClickEvent -> getListener().onComponentEvent(null));
    }

    public String getName() {
        return name;
    }

    public Component getIcon() {
        return icon;
    }

    public ComponentEventListener<ClickEvent<?>> getListener() {
        return listener;
    }
}
