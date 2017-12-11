package com.github.appreciated.app.layout.builder;

import com.vaadin.ui.Component;

@FunctionalInterface
public interface ComponentProvider<T extends Component, V> extends Provider<T, V> {
}
