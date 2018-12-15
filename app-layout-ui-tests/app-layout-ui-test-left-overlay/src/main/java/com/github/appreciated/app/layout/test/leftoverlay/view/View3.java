package com.github.appreciated.app.layout.test.leftoverlay.view;

import com.github.appreciated.app.layout.test.leftoverlay.LeftOverlayBehaviourView;
import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = LeftOverlayBehaviourView.class) // an empty view name will also be the default view
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
