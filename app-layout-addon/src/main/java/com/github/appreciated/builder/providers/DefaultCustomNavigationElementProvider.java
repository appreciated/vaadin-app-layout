package com.github.appreciated.builder.providers;

import com.github.appreciated.builder.ComponentProvider;
import com.github.appreciated.builder.component.NavigationButton;
import com.github.appreciated.builder.elements.CustomNavigatorNavigationElement;
import com.vaadin.ui.Component;


public class DefaultCustomNavigationElementProvider implements ComponentProvider<CustomNavigatorNavigationElement> {
    @Override
    public Component getComponent(CustomNavigatorNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addClickListener(element.getListener());
        return button;
    }
}
