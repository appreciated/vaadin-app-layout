package com.github.appreciated.app.layout.test.leftresponsivehybridnoappbar.view;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.github.appreciated.app.layout.test.leftresponsivehybridnoappbar.LeftResponsiveHybridNoAppBarBehaviourView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftResponsiveHybridNoAppBarBehaviourView.class)
@Caption("Home View")
@Icon(VaadinIcon.HOME)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
