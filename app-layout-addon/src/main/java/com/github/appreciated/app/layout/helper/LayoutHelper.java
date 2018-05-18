package com.github.appreciated.app.layout.helper;

import com.vaadin.flow.component.Component;

public class LayoutHelper {
    public static void makeScrollable(Component component) {
        component.getElement().getStyle().set("overflow", "auto");
    }

    public static void disableFlex(Component component) {
        component.getElement().getStyle().set("display", "block");
    }
}
