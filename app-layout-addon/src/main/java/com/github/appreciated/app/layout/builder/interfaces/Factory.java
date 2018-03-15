package com.github.appreciated.app.layout.builder.interfaces;

import java.io.Serializable;

/**
 * An interface for classes that are able to give a specific output instance of the type {@link T} for an instance of the type {@link V} as input
 * @param <T> the output
 * @param <V> the input
 */

@FunctionalInterface
public interface Factory<T, V> extends Serializable {
    T get(V info);
}
