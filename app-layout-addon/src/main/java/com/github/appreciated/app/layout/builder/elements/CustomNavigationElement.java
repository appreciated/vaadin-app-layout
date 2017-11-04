package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.ui.Component;

public class CustomNavigationElement extends AbstractNavigationElement<Component, CustomNavigationElement> {
    private Component element;

    public CustomNavigationElement(Component element) {
        this.element = element;
        setProvider(info -> element);
    }

    @Override
    CustomNavigationElement getInfo() {
        return this;
    }

    @Override
    public void setProvider(AppLayout provider) {

    }

    @Override
    public void setProvider(AppLayout provider, AppLayout.Position position) {

    }
}
