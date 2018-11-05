package com.github.appreciated.example;

import com.vaadin.flow.router.Route;

@Route(value = "viewv", layout = MainAppLayout.class)
public class View7 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}