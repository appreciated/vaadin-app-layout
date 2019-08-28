package com.github.appreciated.app.layout.addons.search.overlay;

import com.vaadin.flow.component.Component;

import java.util.stream.Stream;

public interface DataViewProvider<T> {
    Stream<Component> getComponentsForQueryResult(Stream<T> queryResult);
}
