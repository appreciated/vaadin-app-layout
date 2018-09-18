package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.Component;

/**
 * An interface for classes that are able to give a specific output {@link Component} for two instances as input one of the type {@link S} and one of the type {@link T}
 *
 * @param <S> the first input
 * @param <T> the secound input
 */

@FunctionalInterface
public interface PairComponentFactory<S, T> {
    Component getComponent(S holder, T info);
}
