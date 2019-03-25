package com.github.appreciated.app.layout;

import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = MainLayout.class)
public class View4 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}