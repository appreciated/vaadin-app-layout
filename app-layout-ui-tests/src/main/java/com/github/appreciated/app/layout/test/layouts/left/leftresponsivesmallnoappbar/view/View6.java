package com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmallnoappbar.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmallnoappbar.LeftResponsiveSmallNoAppBarBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = LeftResponsiveSmallNoAppBarBehaviourView.class)
// an empty view name will also be the default view
public class View6 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
