package com.github.appreciated.app.layout.component.menu.left.items;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * A wrapper class for a menu element that is a {@link LeftSectionItem}.
 */
public class LeftSectionItem extends HorizontalLayout {

    private Label sectionLabel;
    private String name;

    public LeftSectionItem(String name) {
        this();
        this.name = name;
        sectionLabel = new Label(name);
        sectionLabel.getStyle().set("font-size", "var(--app-layout-font-size-menu)");

        add(sectionLabel);
        getStyle().set("padding","var(--app-layout-space-s) var(--app-layout-space-s) 0 var(--app-layout-space-s)");
    }

    public LeftSectionItem() {
        getStyle().set("border-top", "1px solid var(--app-layout-section-divider-color)");
        setWidth("100%");
    }

    public String getName() {
        return name;
    }
}
