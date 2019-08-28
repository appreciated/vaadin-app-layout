package com.github.appreciated.app.layout.addons.search;

import com.vaadin.flow.component.Component;

import java.util.stream.Stream;

public class DefaultSearchProvider implements DataViewProvider<DefaultSearchResult> {

    @Override
    public Stream<Component> getViewsForResult(Stream<DefaultSearchResult> result) {
        return result.map(DefaultSearchResultView::new);
    }
}
