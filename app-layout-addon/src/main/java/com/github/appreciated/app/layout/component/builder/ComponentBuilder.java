package com.github.appreciated.app.layout.component.builder;

import com.vaadin.flow.component.Component;

@FunctionalInterface
public interface ComponentBuilder<T extends Component> {
    T build();
}
