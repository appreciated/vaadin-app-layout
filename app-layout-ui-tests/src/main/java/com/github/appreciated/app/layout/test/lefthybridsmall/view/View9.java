package com.github.appreciated.app.layout.test.lefthybridsmall.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.lefthybridsmall.LeftHybridSmallBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view9", layout = LeftHybridSmallBehaviourView.class) // an empty view name will also be the default view
public class View9 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
