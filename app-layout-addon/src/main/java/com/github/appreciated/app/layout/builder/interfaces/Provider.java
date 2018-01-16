package com.github.appreciated.app.layout.builder.interfaces;

@FunctionalInterface
public interface Provider<T, V> {
    T get(V info);
}
