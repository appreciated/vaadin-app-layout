package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.HasElement;

@FunctionalInterface
public interface NavigationElement {
    boolean setActiveNavigationComponent(Class<? extends HasElement> element);
}
