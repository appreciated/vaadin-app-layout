package com.github.appreciated.app.layout.component;

import com.vaadin.ui.Component;

/**
 * The implementation of AbstractFlexBoxLayout which mimics some behaviour of the VerticalLayout by using Flexbox
 */
public class VerticalFlexBoxLayout extends AbstractFlexBoxLayout {
    public VerticalFlexBoxLayout() {
        super("flex-column");
    }

    public VerticalFlexBoxLayout(Component... components) {
        super("flex-column", components);
    }
}
