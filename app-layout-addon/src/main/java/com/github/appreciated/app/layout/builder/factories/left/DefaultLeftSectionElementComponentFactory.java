package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Label;


public class DefaultLeftSectionElementComponentFactory implements ComponentFactory<HasElement, SectionNavigationElement> {
    @Override
    public HasElement get(SectionNavigationElement element) {
        Label label = new Label(element.getName());
        return label;
    }
}
