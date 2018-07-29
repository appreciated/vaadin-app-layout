package com.github.appreciated.app.layout.test.view;

import com.github.appreciated.app.layout.test.uis.left.LeftResponsiveBehavior;
import com.github.appreciated.applayout.annotations.MenuCaption;
import com.github.appreciated.applayout.annotations.MenuIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = LeftResponsiveBehavior.class)
@MenuCaption("Home View")
@MenuIcon(VaadinIcon.HOME)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
