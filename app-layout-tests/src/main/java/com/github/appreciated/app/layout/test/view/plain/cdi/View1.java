package com.github.appreciated.app.layout.test.view.plain.cdi;

import com.github.appreciated.app.layout.test.view.ExampleView;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = "")
public class View1 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
