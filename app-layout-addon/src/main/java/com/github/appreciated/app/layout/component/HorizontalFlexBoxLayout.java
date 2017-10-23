package com.github.appreciated.app.layout.component;

import com.vaadin.ui.Component;

public class HorizontalFlexBoxLayout extends AbstractFlexBoxLayout {
    public HorizontalFlexBoxLayout() {
        super("flex-row");
    }

    public HorizontalFlexBoxLayout(Component... components) {
        super("flex-row", components);
    }
}
