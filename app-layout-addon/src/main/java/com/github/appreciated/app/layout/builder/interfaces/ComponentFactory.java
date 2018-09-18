package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.HasElement;

/**
 * An interface for classes that are able to give a specific output instance of the type {@link T} for an instance of the type {@link V} as input that is required to extend {@link Component}
 *
 * @param <T> the output
 * @param <V> the input
 */

@FunctionalInterface
public interface ComponentFactory<T extends HasElement, V> extends Factory<T, V> {
}
