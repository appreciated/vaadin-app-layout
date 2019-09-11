package com.github.appreciated.app.layout.test.addon.search.view;

import com.github.appreciated.app.layout.test.addon.search.SearchView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = SearchView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
