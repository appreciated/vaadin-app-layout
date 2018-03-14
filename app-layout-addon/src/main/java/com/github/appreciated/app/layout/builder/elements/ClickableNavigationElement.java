package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

/**
 * A wrapper class for {@link ClickableNavigationElement}.
 */
public class ClickableNavigationElement extends AbstractNavigationElement<Component, ClickableNavigationElement> {
    private final String name;
    private Resource icon;
    private Button.ClickListener listener;

    public ClickableNavigationElement(String name, Resource icon, Button.ClickListener listener) {
        this.name = name;
        this.icon = icon;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public Resource getIcon() {
        return icon;
    }

    @Override
    ClickableNavigationElement getInfo() {
        return this;
    }

    @Override
    public void setProvider(AppLayoutComponent provider) {
        setProvider(provider.getDrawerClickableElementProvider());
    }

    @Override
    public void setProvider(AppLayoutComponent provider, Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerClickableElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopClickableElementProvider());
                break;
        }
    }

    public Button.ClickListener getListener() {
        return listener;
    }
}
