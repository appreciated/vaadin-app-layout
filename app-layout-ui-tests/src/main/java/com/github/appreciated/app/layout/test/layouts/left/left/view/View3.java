package com.github.appreciated.app.layout.test.layouts.left.left.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.layouts.left.left.LeftBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = LeftBehaviourView.class) // an empty view name will also be the default view
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
