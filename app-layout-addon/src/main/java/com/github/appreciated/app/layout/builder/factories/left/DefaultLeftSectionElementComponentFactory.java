package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.SectionNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerSubheader;
import com.vaadin.flow.component.HasElement;

public class DefaultLeftSectionElementComponentFactory implements ComponentFactory<HasElement, SectionNavigationElement> {
    @Override
    public HasElement get(SectionNavigationElement element) {
        PaperDrawerSubheader label = new PaperDrawerSubheader(element.getName());
        label.getElement().getStyle().set("width", "100%");
        return label;
    }
}
