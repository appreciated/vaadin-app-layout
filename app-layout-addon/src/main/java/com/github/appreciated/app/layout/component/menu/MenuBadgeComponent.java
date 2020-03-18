package com.github.appreciated.app.layout.component.menu;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.Span;

public class MenuBadgeComponent extends Span implements HasStyle {
    private static final long serialVersionUID = 1L;

    public MenuBadgeComponent() {
        setWidth("var(--app-layout-badge-width)");
        setHeight("var(--app-layout-badge-height)");

        getStyle().set("background", "var(--app-layout-badge-background)")
                .set("color", "var(--app-layout-badge-font-color)")
                .set("border-radius", "25px")
                .set("line-height", "var(--app-layout-badge-height)")
                .set("text-align", "center")
                .set("top", "var(--app-layout-badge-top)")
                .set("right", "var(--app-layout-badge-right)")
                .set("position", "absolute")
                .set("top", "var(--app-layout-badge-top)")
                .set("transform", "translate(0%, -50%)")
                .set("margin-right", "10px");
    }

}
