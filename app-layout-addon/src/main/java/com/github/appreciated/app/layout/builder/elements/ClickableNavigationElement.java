package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Position;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for {@link ClickableNavigationElement}.
 */
public class ClickableNavigationElement extends AbstractNavigationElement<HasElement, ClickableNavigationElement> {
    private final String name;
    private Icon icon;
    private ComponentEventListener<ClickEvent<?>> listener;

    public ClickableNavigationElement(String name, Icon icon, ComponentEventListener<ClickEvent<?>> listener) {
        this.name = name;
        this.icon = icon;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    ClickableNavigationElement getInfo() {
        return this;
    }

    @Override
    public void setProvider(AppLayoutElementBase provider) {
        setProvider(provider.getDrawerClickableElementProvider());
    }

    @Override
    public void setProvider(AppLayoutElementBase provider, Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerClickableElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopClickableElementProvider());
                break;
        }
    }

    public ComponentEventListener<ClickEvent<?>> getListener() {
        return listener;
    }
}
