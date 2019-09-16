package com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmall.view;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.layouts.left.leftresponsivesmall.LeftResponsiveSmallBehaviourView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftResponsiveSmallBehaviourView.class)
@Caption("Home View")
@Icon(VaadinIcon.HOME)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
