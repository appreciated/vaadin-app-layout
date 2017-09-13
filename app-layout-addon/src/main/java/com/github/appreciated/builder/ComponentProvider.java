package com.github.appreciated.builder;

import com.vaadin.ui.Component;

public interface ComponentProvider<T> {
    Component getComponent(T info);
}
