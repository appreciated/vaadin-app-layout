package com.github.appreciated.app.layout.test.behavior.leftoverlay.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.behavior.leftoverlay.LeftOverlayBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view7", layout = LeftOverlayBehaviourView.class) // an empty view name will also be the default view
public class View7 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
