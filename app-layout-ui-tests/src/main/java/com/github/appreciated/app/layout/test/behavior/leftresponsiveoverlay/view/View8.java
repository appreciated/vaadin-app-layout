package com.github.appreciated.app.layout.test.behavior.leftresponsiveoverlay.view;

import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.behavior.leftresponsiveoverlay.LeftResponsiveOverlayBehaviourView;
import com.vaadin.flow.router.Route;

@Route(value = "view8", layout = LeftResponsiveOverlayBehaviourView.class)
// an empty view name will also be the default view
public class View8 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
