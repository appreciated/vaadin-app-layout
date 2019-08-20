package com.github.appreciated.app.layout.test.navigation.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.navigation.NavigationView;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = NavigationView.class)
public class View2 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
