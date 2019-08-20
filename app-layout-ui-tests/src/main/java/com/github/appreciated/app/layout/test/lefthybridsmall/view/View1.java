package com.github.appreciated.app.layout.test.lefthybridsmall.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.lefthybridsmall.LeftHybridSmallBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftHybridSmallBehaviourView.class)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
