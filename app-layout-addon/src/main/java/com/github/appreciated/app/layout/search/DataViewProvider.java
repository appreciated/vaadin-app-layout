package com.github.appreciated.app.layout.search;

import com.vaadin.flow.component.Component;

import java.util.stream.Stream;

public interface DataViewProvider<T> {
    Stream<Component> getViewsForResult(Stream<T> result);
}
