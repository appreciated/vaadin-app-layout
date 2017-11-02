package com.github.appreciated.app.layout.builder.providers.top;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;
import com.vaadin.ui.Component;

public class DefaultTopSubmenuNavigationElementProvider implements ComponentProvider<Component, SubmenuNavigationElement> {
    @Override
    public Component get(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        element.getSubmenuElements().forEach(element1 -> container.addComponent(element1.getComponent()));
        return container;
    }
}