package com.github.appreciated.app.layout.component.layout;

import com.vaadin.ui.Component;

/**
 * The implementation of AbstractFlexBoxLayout which mimics some behaviour of the HorizontalLayout by using Flexbox
 */
public class HorizontalFlexBoxLayout extends AbstractFlexBoxLayout {
    public HorizontalFlexBoxLayout() {
        super("flex-row");
    }

    public HorizontalFlexBoxLayout(Component... components) {
        super("flex-row", components);
    }
}
