package com.github.appreciated.app.layout.test.behavior.left.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.behavior.left.LeftBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftBehaviourView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
