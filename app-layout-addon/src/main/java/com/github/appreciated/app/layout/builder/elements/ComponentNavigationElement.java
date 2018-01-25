package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.vaadin.ui.Component;

/**
 * A wrapper class for a MenuElement that is simply a Component.
 */
public class ComponentNavigationElement extends AbstractNavigationElement<Component, ComponentNavigationElement> {
    private Component element;

    public ComponentNavigationElement(Component element) {
        this.element = element;
        setProvider(info -> element);
    }

    @Override
    ComponentNavigationElement getInfo() {
        return this;
    }

    @Override
    public void setProvider(AppLayoutComponent provider) {

    }

    @Override
    public void setProvider(AppLayoutComponent provider, Position position) {

    }
}
