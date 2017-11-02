package com.github.appreciated.app.layout.builder.providers.left;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;
import com.vaadin.ui.Component;

public class DefaultLeftSubmenuNavigationElementProvider implements ComponentProvider<Component, SubmenuNavigationElement> {
    @Override
    public Component get(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        element.getSubmenuElements().forEach(element1 -> container.addComponent(element1.getComponent()));
        return container;
    }
}