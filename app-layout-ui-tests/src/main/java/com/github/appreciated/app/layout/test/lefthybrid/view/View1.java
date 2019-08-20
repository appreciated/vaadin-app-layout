package com.github.appreciated.app.layout.test.lefthybrid.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.lefthybrid.LeftHybridBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftHybridBehaviourView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
