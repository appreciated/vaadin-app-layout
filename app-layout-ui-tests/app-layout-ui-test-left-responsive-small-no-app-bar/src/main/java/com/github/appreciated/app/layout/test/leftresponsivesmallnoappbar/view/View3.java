package com.github.appreciated.app.layout.test.leftresponsivesmallnoappbar.view;

import com.github.appreciated.app.layout.test.leftresponsivesmallnoappbar.LeftResponsiveSmallNoAppBarBehaviourView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = LeftResponsiveSmallNoAppBarBehaviourView.class)
// an empty view name will also be the default view
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
