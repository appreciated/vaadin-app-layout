package com.github.appreciated.basic.test.view.annotated.basic;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.github.appreciated.basic.test.view.ExampleView;

import com.vaadin.icons.VaadinIcons;

@NavigatorViewName("") // an empty view name will also be the default view
@MenuCaption("Home View")
@MenuIcon(VaadinIcons.HOME)
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
