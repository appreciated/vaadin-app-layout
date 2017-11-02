package com.github.appreciated.app.layout.builder.providers.left;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class DefaultLeftSectionElementComponentProvider implements ComponentProvider<Component, SectionNavigationElement> {
    @Override
    public Component get(SectionNavigationElement element) {
        Label label = new Label(element.getName());
        label.addStyleName("app-layout-menu-section");
        label.setWidth(100, Sizeable.Unit.PERCENTAGE);
        return label;
    }
}
