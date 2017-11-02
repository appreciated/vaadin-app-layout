package com.github.appreciated.app.layout.builder.elements;

import com.vaadin.ui.Component;

public class SectionNavigationElement extends AbstractNavigationElement<Component, SectionNavigationElement> {

    private String name;

    public SectionNavigationElement(String name) {
        this.name = name;
    }

    @Override
    SectionNavigationElement getInfo() {
        return this;
    }

    public String getName() {
        return name;
    }
}
