package com.github.appreciated.app.layout.test.nestedlayout.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.nestedlayout.NestedLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view7", layout = NestedLayout.class) // an empty view name will also be the default view
public class View7 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
