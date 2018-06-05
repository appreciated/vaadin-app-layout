package com.github.appreciated.app.layout.helper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.dom.Element;

public class LayoutHelper {
    public static void makeScrollable(Component component) {
        component.getElement().getStyle().set("overflow", "auto");
    }

    public static void disableFlex(Component component) {
        component.getElement().getStyle().set("display", "block");
    }

    public static void add(HasComponents element, Component... components) {
        if (components == null) {
            throw new AssertionError();
        } else {
            int length = components.length;
            for (int i = 0; i < length; ++i) {
                Component component = components[i];
                if (component == null) {
                    throw new AssertionError();
                }
                element.getElement().appendChild(new Element[]{component.getElement()});
            }
        }
    }
}
