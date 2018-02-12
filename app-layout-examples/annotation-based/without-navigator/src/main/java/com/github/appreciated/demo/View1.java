package com.github.appreciated.demo;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.vaadin.icons.VaadinIcons;

@NavigatorViewName("") // in Spring an empty view name is also the default view
@MenuCaption("Home View")
@MenuIcon(VaadinIcons.HOME)
public class View1 extends ExampleView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}
