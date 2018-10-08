package com.github.appreciated.app.layout.helper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;

public class LayoutHelper {

    public static void add(HasComponents element, Component... components) {
        if (components == null) {
            throw new AssertionError();
        } else {
            for (final Component component : components) {
                if (component == null) {
                    throw new AssertionError();
                }
                element.getElement().appendChild(component.getElement());
            }
        }
    }
}
