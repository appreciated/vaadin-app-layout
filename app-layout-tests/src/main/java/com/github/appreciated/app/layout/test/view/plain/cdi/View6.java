package com.github.appreciated.app.layout.test.view.plain.cdi;

import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "view6") // the path under which you can navigate to it
public class View6 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
