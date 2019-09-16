package com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmall.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmall.LeftResponsiveSmallBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = LeftResponsiveSmallBehaviourView.class)
// an empty view name will also be the default view
public class View2 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
