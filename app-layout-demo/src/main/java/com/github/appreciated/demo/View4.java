package com.github.appreciated.demo;

import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = MainView.class)
public class View4 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}