package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.HasElement;

public interface NavigationElement {
    boolean setActiveNavigationElement(Class<? extends HasElement> element);

    boolean hasNavigationElement(Class<? extends HasElement> element);
}
