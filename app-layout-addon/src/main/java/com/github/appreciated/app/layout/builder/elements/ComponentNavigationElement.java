package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Position;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

/**
 * A wrapper class for a menu element that is a {@link HasElement}.
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
    public void setProvider(AppLayoutElementBase provider) {

    }

    @Override
    public void setProvider(AppLayoutElementBase provider, Position position) {

    }
}
