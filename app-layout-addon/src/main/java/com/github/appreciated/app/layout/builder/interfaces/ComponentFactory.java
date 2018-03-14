package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.ui.Component;

@FunctionalInterface
public interface ComponentFactory<T extends Component, V> extends Factory<T, V> {
}
