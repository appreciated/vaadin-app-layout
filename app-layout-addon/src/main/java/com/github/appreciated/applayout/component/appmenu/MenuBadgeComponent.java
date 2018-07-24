package com.github.appreciated.applayout.component.appmenu;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.Label;

public class MenuBadgeComponent extends Label implements HasStyle {

    public MenuBadgeComponent(String label) {
        this();
        if (label != null) {
            setText(label);
        }

    }

    public MenuBadgeComponent() {
        setWidth("var(--app-layout-badge-width)");
        setHeight("var(--app-layout-badge-height)");

        getStyle().set("background", "var(--app-layout-bar-background-color)")
                .set("color", "var(--app-layout-badge-color)")
                .set("border-radius", "25px")
                .set("line-height", "var(--app-layout-badge-height)")
                .set("text-align", "center")
                .set("position", "relative")
                .set("top", "var(--app-layout-badge-top)")
                .set("right", "var(--app-layout-badge-right)");
    }

}
