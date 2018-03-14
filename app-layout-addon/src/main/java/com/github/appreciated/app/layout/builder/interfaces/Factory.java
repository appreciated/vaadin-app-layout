package com.github.appreciated.app.layout.builder.interfaces;

import java.io.Serializable;

/**
 * A {@link Factory} instance produces for a specific input {@link T} a specific output {@link T}
 *
 * @param <T>
 * @param <V>
 */
@FunctionalInterface
public interface Factory<T, V> extends Serializable {
    T get(V info);
}
