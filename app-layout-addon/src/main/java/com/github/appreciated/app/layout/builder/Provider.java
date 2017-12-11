package com.github.appreciated.app.layout.builder;

@FunctionalInterface
public interface Provider<T, V> {
    T get(V info);
}
