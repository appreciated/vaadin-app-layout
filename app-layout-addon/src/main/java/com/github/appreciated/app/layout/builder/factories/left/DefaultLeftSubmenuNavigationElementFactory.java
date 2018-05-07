package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;

public class DefaultLeftSubmenuNavigationElementFactory implements ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> {
    @Override
    public SubmenuNavigationElement.SubmenuElement get(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        element.getSubmenuElements().forEach(element1 -> container.addComponent(element1.getComponent()));
        return container;
    }
}