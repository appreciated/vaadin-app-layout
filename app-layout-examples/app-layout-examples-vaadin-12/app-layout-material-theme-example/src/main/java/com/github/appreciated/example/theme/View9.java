package com.github.appreciated.example.theme;

import com.vaadin.flow.router.Route;

@Route(value = "view9", layout = MainAppLayout.class)
public class View9 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}