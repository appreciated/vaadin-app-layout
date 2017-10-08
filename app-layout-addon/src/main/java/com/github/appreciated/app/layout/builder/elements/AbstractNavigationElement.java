package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.vaadin.ui.Component;

public abstract class AbstractNavigationElement<T> {

    ComponentProvider<T> provider;
    private Component component;

    abstract T getInfo();

    public ComponentProvider<T> getProvider() {
        return provider;
    }

    public void setProvider(ComponentProvider<T> provider) {
        this.provider = provider;
    }

    public Component getComponent() {
        if (component == null) {
            component = provider.get(getInfo());
        }
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
