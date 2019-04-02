package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.Component;

import java.util.stream.Stream;

/**
 * This interface is for Components that contain Elements that are clickable and the interaction with it leads to route
 * changes.
 * This is especially important to highlight navigation elements if the view they are related with was navigated to,
 * without the elements knowledge (f.e. loading the page with a nested url).
 */
public interface NavigationElementContainer extends NavigationElement {

    default void setActiveNavigationElement(boolean active) {
    }

    default void applyParentToItems(Stream<Component> elements) {
        elements.filter(component -> component instanceof NavigationElement)
                .map(component -> (NavigationElement) component)
                .forEach(this::applyParentToItem);
    }

    default void applyParentToItem(NavigationElement element) {
        element.setNavigationElementContainer(this);
    }

}
