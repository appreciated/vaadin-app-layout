package com.github.appreciated.app.layout.test.layouts.left.leftresponsivedouble.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.layouts.left.leftresponsivedouble.LeftResponsiveDoubleBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = LeftResponsiveDoubleBehaviourView.class)
// an empty view name will also be the default view
public class View4 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
