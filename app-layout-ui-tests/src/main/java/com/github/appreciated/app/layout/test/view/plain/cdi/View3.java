package com.github.appreciated.app.layout.test.view.plain.cdi;

import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "view3") // the path under which you can navigate to it
public class View3 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
