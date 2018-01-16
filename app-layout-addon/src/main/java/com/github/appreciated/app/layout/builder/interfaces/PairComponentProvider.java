package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.ui.Component;

@FunctionalInterface
public interface PairComponentProvider<S, T> {
    Component getComponent(S holder, T info);
}
