package com.github.appreciated.basic.test.view.annotated.cdi;

import com.github.appreciated.app.layout.annotations.MenuCaption;
import com.github.appreciated.app.layout.annotations.MenuIcon;
import com.github.appreciated.basic.test.view.ExampleView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "view2") // the path under which you can navigate to it
@MenuCaption("View 2")
@MenuIcon(VaadinIcons.COG)
public class View2 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
