package com.github.appreciated.app.layout.test.top.large.view;

import com.github.appreciated.app.layout.test.top.large.TopLargeBehaviourView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = TopLargeBehaviourView.class)
// an empty view name will also be the default view
public class View2 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
