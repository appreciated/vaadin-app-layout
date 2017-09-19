package com.github.appreciated.app.layout.builder;

import com.vaadin.ui.Component;

public interface ComponentProvider<T> {
    Component getComponent(T info);
}
