package com.github.appreciated.spring;

import com.vaadin.flow.router.Route;

@Route(value = "view5", layout = MainAppLayout.class)
public class View5 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}