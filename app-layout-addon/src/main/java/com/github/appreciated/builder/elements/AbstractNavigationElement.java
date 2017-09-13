package com.github.appreciated.builder.elements;

import com.github.appreciated.builder.ComponentProvider;
import com.vaadin.ui.Component;

public abstract class AbstractNavigationElement<T> {

    ComponentProvider<T> provider;

    abstract T getInfo();

    public ComponentProvider<T> getProvider() {
        return provider;
    }

    public void setProvider(ComponentProvider<T> provider) {
        this.provider = provider;
    }

    public Component getComponent() {
        return provider.getComponent(getInfo());
    }

}
