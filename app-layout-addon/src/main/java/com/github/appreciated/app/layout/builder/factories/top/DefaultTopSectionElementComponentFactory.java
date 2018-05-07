package com.github.appreciated.app.layout.builder.factories.top;

import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Label;

public class DefaultTopSectionElementComponentFactory implements ComponentFactory<HasElement, SectionNavigationElement> {
    @Override
    public HasElement get(SectionNavigationElement element) {
        Label label = new Label(element.getName());
        label.getElement().getClassList().add("app-layout-menu-section");
        label.setWidth("100%");
        return label;
    }
}
