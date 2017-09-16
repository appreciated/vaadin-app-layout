package com.github.appreciated.builder.elements;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

public class CustomNavigatorNavigationElement extends AbstractNavigationElement<CustomNavigatorNavigationElement> {
    private final String name;
    private Resource icon;
    private Button.ClickListener listener;

    public CustomNavigatorNavigationElement(String name, Resource icon, Button.ClickListener listener) {
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
    CustomNavigatorNavigationElement getInfo() {
        return this;
    }

    public Button.ClickListener getListener() {
        return listener;
    }
}
