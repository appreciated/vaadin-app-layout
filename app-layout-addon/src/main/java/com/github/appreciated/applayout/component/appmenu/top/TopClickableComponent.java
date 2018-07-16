package com.github.appreciated.applayout.component.appmenu.top;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for {@link TopClickableComponent}.
 */
public class TopClickableComponent extends Button {
    private String name;
    private Icon icon;
    private ComponentEventListener<ClickEvent<?>> listener;

    public TopClickableComponent(String name, Icon icon, ComponentEventListener<ClickEvent<?>> listener) {
        super(name, icon);
        this.name = name;
        this.icon = icon;
        this.listener = listener;
        getElement().setAttribute("theme", "tertiary");
        setHeight("calc(var(--app-bar-height) - 4px)");
        addClickListener(appMenuIconItemClickEvent -> getListener().onComponentEvent(null));
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
