package com.github.appreciated.app.layout.test.navigation.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.navigation.NavigationView;
import com.vaadin.flow.router.Route;

@Route(value = "view8", layout = NavigationView.class)
public class View8 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
