package com.github.appreciated.app.layout;

import com.vaadin.flow.router.Route;

@Route(value = "view8", layout = MainLayout.class)
public class View8 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}