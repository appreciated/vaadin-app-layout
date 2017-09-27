package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;
import com.vaadin.ui.Component;

public class DefaultSubmenuNavigationElementProvider implements ComponentProvider<SubmenuNavigationElement> {
    @Override
    public Component getComponent(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        element.getSubmenuElements().forEach(element1 -> container.addComponent(element1.getComponent()));
        return container;
    }
}