package com.github.appreciated.demo;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.vaadin.icons.VaadinIcons;

@NavigatorViewName("view6") // the path under which you can navigate to it
@MenuCaption("View 6")
@MenuIcon(VaadinIcons.HOME)
public class View6 extends ExampleView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}
