package com.github.appreciated.demo;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.vaadin.icons.VaadinIcons;

@NavigatorViewName("view2") // the path under which you can navigate to it
@MenuCaption("View 2")
@MenuIcon(VaadinIcons.COG)
public class View2 extends ExampleView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}
