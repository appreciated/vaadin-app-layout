package com.github.appreciated.applayout.component.appmenu;

import com.vaadin.flow.component.html.Label;

public class MenuBadgeComponent extends Label {

    public MenuBadgeComponent(String label) {
        this();
        if (label != null) {
            setText(label);
        }
    }

    public MenuBadgeComponent() {
        setWidth("25px");
        setHeight("25px");

        getElement().getStyle()
                .set("background", "var(--app-layout-bar-background-color)")
                .set("color", "var(--app-layout-bar-font-color)")
                .set("border-radius", "25px")
                .set("line-height", "25px")
                .set("text-align", "center");
    }

}
