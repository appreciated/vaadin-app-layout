package com.github.appreciated.app.layout.builder.elements;

import com.vaadin.ui.Component;

public class CustomNavigationElement extends AbstractNavigationElement<CustomNavigationElement> {
    private Component element;

    public CustomNavigationElement(Component element) {
        this.element = element;
        setProvider(info -> element);
    }

    @Override
    CustomNavigationElement getInfo() {
        return this;
    }
}
