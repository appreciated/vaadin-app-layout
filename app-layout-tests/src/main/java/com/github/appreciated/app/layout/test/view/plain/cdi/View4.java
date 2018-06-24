package com.github.appreciated.app.layout.test.view.plain.cdi;

import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "view4") // the path under which you can navigate to it
public class View4 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
