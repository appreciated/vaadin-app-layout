package com.github.appreciated.app.layout.test.leftresponsivesmall.view;

import com.github.appreciated.app.layout.test.leftresponsivesmall.LeftResponsiveSmallBehaviourView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view8", layout = LeftResponsiveSmallBehaviourView.class)
// an empty view name will also be the default view
public class View8 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
