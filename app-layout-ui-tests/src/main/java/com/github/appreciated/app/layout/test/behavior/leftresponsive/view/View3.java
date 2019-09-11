package com.github.appreciated.app.layout.test.behavior.leftresponsive.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.behavior.leftresponsive.LeftResponsiveBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = LeftResponsiveBehaviourView.class) // an empty view name will also be the default view
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
