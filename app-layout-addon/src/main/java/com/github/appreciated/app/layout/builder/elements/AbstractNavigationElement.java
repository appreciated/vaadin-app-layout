package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.vaadin.ui.Component;

/**
 * AbstractNavigationElement is a abstract wrapper class for a Component which will be provided by a ComponentProvider.
 *
 * @param <V> The specific Component which can be produced by this wrapper
 * @param <T> The Element itself to only allow Providers to be used that are compatible with the specific AbstractNavigationElement
 */
public abstract class AbstractNavigationElement<V extends Component, T> {

    ComponentProvider<V, T> provider;
    private V component;

    abstract T getInfo();

    public ComponentProvider<V, T> getProvider() {
        return provider;
    }

    public abstract void setProvider(AppLayout provider);

    public void setProvider(ComponentProvider<V, T> provider) {
        this.component = null;
        this.provider = provider;
    }

    public abstract void setProvider(AppLayout provider, AppLayout.Position position);

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
