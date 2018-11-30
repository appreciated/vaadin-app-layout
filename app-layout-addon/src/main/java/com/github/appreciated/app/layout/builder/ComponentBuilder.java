package com.github.appreciated.app.layout.builder;

import com.vaadin.flow.component.Component;

@FunctionalInterface
public interface ComponentBuilder {
    Component build();
}
