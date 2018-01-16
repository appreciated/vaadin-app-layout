package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.ui.Component;

@FunctionalInterface
public interface ComponentProvider<T extends Component, V> extends Provider<T, V> {
}
