package com.github.appreciated.app.layout.test.addon.search.view;

import com.github.appreciated.app.layout.test.addon.search.SearchView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view7", layout = SearchView.class) // an empty view name will also be the default view
public class View7 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
