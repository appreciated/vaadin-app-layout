package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.vaadin.ui.Component;

/**
 * AbstractNavigationElement is a wrapper class for a first unspecific Component.
 * The Component is supposed to be provided by a functional provider.
 *
 * @param <V>
 * @param <T>
 */
public abstract class AbstractNavigationElement<V extends Component, T> {

    ComponentProvider<V, T> provider;
    private V component;

    abstract T getInfo();

    public ComponentProvider<V, T> getProvider() {
        return provider;
    }

    public void setProvider(ComponentProvider<V, T> provider) {
        this.provider = provider;
    }

    public V getComponent() {
        if (provider == null) {
            throw new IllegalStateException("Provider must not be null");
        }
        if (component == null) {
            component = provider.get(getInfo());
        }
        return component;
    }

    public void setComponent(V component) {
        this.component = component;
    }

}
