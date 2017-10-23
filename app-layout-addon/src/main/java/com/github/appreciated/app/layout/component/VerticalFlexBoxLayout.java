package com.github.appreciated.app.layout.component;

import com.vaadin.ui.Component;

public class VerticalFlexBoxLayout extends AbstractFlexBoxLayout {
    public VerticalFlexBoxLayout() {
        super("flex-column");
    }

    public VerticalFlexBoxLayout(Component... components) {
        super("flex-column", components);
    }
}
