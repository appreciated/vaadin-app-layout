package com.github.appreciated.app.layout.builder.interfaces;

import java.io.Serializable;

@FunctionalInterface
public interface Provider<T, V> extends Serializable {
    T get(V info);
}
