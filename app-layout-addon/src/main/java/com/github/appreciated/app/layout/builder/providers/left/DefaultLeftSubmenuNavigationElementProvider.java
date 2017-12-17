package com.github.appreciated.app.layout.builder.providers.left;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;

public class DefaultLeftSubmenuNavigationElementProvider implements ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> {
    @Override
    public SubmenuNavigationElement.SubmenuComponent get(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        element.getSubmenuElements().forEach(element1 -> container.addComponent(element1.getComponent()));
        return container;
    }
}