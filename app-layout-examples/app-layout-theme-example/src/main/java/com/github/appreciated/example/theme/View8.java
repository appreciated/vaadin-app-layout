package com.github.appreciated.example.theme;

import com.vaadin.flow.router.Route;

@Route(value = "view8", layout = MainAppLayout.class)
public class View8 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}