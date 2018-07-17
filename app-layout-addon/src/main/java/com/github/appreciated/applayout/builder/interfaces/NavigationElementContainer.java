package com.github.appreciated.applayout.builder.interfaces;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

import java.util.stream.Stream;

/**
 * This interface is for Components that contain Elements that are clickable and the interaction with it leads to route
 * changes.
 * This is especially important to highlight navigation elements if the view they are related with was navigated to,
 * without the elements knowledge (f.e. loading the page with a nested url).
 */
public interface NavigationElementContainer extends NavigationElementComponent {

    default boolean setActiveNavigationElementWithViewClass(HasElement element) {
        return setActiveNavigationElementWithViewClass(getChildren(), element);
    }

    default boolean setActiveNavigationElementWithViewClass(Stream<Component> elements, HasElement content) {
        return elements
                .filter(component -> component instanceof NavigationElementComponent)
                .map(component -> ((NavigationElementComponent) component).setActiveNavigationElementWithViewClass(content))
                .reduce((first, next) -> first || next).orElse(false);
    }

    Stream<Component> getChildren();

    Component getComponent();
}
