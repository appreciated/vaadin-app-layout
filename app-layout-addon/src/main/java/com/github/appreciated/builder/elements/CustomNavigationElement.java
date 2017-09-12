package com.github.appreciated.builder.elements;

import com.vaadin.ui.Component;

public class CustomNavigationElement extends AbstractNavigationElement {
    private Component element;

    public CustomNavigationElement(Component element) {
        this.element = element;
    }

    @Override
    public Component getComponent() {
        return element;
    }
}
