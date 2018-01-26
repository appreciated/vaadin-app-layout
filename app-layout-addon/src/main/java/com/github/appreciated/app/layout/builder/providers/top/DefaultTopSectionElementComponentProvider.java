package com.github.appreciated.app.layout.builder.providers.top;

import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

import java.io.Serializable;

public class DefaultTopSectionElementComponentProvider implements Serializable, ComponentProvider<Component, SectionNavigationElement> {
    @Override
    public Component get(SectionNavigationElement element) {
        Label label = new Label(element.getName());
        label.addStyleName("app-layout-menu-section");
        label.setWidth(100, Sizeable.Unit.PERCENTAGE);
        return label;
    }
}
