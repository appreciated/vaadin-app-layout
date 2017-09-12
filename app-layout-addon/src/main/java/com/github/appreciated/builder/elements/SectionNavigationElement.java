package com.github.appreciated.builder.elements;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class SectionNavigationElement extends AbstractNavigationElement {

    private String name;

    public SectionNavigationElement(String name) {
        this.name = name;
    }

    @Override
    public Component getComponent() {
        Label label = new Label(name);
        label.addStyleName("app-layout-menu-section");
        label.setWidth(100, Sizeable.Unit.PERCENTAGE);
        return label;
    }
}
