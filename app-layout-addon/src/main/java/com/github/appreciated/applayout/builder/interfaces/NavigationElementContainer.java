package com.github.appreciated.applayout.builder.interfaces;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

public interface NavigationElementContainer {
    boolean setActiveNavigationElementWithViewClass(HasElement element);

    Component getComponent();
}
