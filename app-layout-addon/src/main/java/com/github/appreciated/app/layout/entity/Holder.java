package com.github.appreciated.app.layout.entity;

/**
 * Simple Data class to hold an instance of the type {@link T}
 *
 * @param <T>
 */
public class Holder<T> {
    public T value;

    public Holder(T value) {
        this.value = value;
    }
}
