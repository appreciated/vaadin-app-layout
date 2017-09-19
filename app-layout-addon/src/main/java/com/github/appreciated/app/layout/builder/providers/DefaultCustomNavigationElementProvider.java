package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.CustomNavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationButton;
import com.vaadin.ui.Component;


public class DefaultCustomNavigationElementProvider implements ComponentProvider<CustomNavigatorNavigationElement> {
    @Override
    public Component getComponent(CustomNavigatorNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addClickListener(element.getListener());
        return button;
    }
}
