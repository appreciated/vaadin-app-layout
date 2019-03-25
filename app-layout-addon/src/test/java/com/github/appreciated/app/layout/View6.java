package com.github.appreciated.app.layout;

import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = MainLayout.class)
public class View6 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}