package com.github.appreciated.app.layout.builder.entities;

/**
 * Simple Data class to hold an instance of a unspecific class
 *
 * @param <T>
 */
public class Holder<T> {
    public T value;

    public Holder(T value) {
        this.value = value;
    }
}
