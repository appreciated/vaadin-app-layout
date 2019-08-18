package com.github.appreciated.spring;

import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = MainAppLayout.class)
public class View6 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}