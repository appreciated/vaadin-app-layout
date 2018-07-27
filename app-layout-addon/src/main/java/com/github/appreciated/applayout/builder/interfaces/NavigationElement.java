package com.github.appreciated.applayout.builder.interfaces;

import com.vaadin.flow.component.HasElement;

public interface NavigationElement {
    boolean setActiveNavigationComponent(Class<? extends HasElement> element);
}
