package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.Component;

/**
 * This interface is for Components that contain Elements that are clickable and the interaction with it leads to route
 * changes.
 * This is especially important to highlight navigation elements if the view they are related with was navigated to,
 * without the elements knowledge (f.e. loading the page with a nested url).
 */
public interface NavigationElementComponent extends NavigationElement {
    void register();
}
