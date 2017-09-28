package com.github.appreciated.app.layout.builder;

public interface Provider<T, V> {
    T getComponent(V info);
}
