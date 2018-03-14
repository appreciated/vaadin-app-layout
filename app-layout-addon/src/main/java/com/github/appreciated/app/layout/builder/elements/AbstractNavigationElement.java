package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.ui.Component;

import java.io.Serializable;

/**
 * Every instance of {@link AbstractNavigationElement} is a wrapper class for a ComponentFactory.
 *
 * @param <V> The specific Component which can be produced by this wrapper
 * @param <T> The Element itself to only allow Providers to be used that are compatible with the specific AbstractNavigationElement
 */
public abstract class AbstractNavigationElement<V extends Component, T> implements Serializable {

    ComponentFactory<V, T> provider;
    private V component;

    abstract T getInfo();

    public ComponentFactory<V, T> getProvider() {
        return provider;
    }

    public abstract void setProvider(AppLayoutComponent provider);

    public void setProvider(ComponentFactory<V, T> provider) {
        this.component = null;
        this.provider = provider;
    }

    public abstract void setProvider(AppLayoutComponent provider, Position position);

    public V getComponent() {
        if (provider == null) {
            throw new IllegalStateException("Factory must not be null");
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
