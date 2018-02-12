package com.github.appreciated.demo;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.app.layout.annotations.NavigatorViewName;
import com.vaadin.icons.VaadinIcons;

@NavigatorViewName("view5") // the path under which you can navigate to it
@MenuCaption("View 5")
@MenuIcon(VaadinIcons.SPLINE_CHART)
public class View5 extends ExampleView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}
