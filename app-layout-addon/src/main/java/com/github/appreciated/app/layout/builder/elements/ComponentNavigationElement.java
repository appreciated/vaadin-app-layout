package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Position;
import com.vaadin.flow.component.HasElement;

/**
 * A wrapper class for a menu element that is a {@link HasElement}.
 */
public class ComponentNavigationElement extends AbstractNavigationElement<HasElement, ComponentNavigationElement> {
    private HasElement element;

    public ComponentNavigationElement(HasElement element) {
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
