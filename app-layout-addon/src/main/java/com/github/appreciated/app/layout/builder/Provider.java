package com.github.appreciated.app.layout.builder;

public interface Provider<T, V> {
    T get(V info);
}
