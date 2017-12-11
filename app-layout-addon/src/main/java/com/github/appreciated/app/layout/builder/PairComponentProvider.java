package com.github.appreciated.app.layout.builder;

import com.vaadin.ui.Component;

@FunctionalInterface
public interface PairComponentProvider<S, T> {
    Component getComponent(S holder, T info);
}
