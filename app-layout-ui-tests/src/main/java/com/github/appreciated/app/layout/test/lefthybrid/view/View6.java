package com.github.appreciated.app.layout.test.lefthybrid.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.lefthybrid.LeftHybridBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = LeftHybridBehaviourView.class) // an empty view name will also be the default view
public class View6 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
