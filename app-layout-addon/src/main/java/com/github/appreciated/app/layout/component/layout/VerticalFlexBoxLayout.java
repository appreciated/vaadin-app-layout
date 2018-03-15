package com.github.appreciated.app.layout.component.layout;

import com.vaadin.ui.Component;

/**
 * The implementation of {@link AbstractFlexBoxLayout} which mimics some behaviour of the {@link com.vaadin.ui.VerticalLayout} by also using "flexbox" via css
 */
public class VerticalFlexBoxLayout extends AbstractFlexBoxLayout {
    public VerticalFlexBoxLayout() {
        super("flex-column");
    }

    public VerticalFlexBoxLayout(Component... components) {
        super("flex-column", components);
    }
}
