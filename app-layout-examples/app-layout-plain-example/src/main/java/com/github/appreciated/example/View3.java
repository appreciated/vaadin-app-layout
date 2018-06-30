package com.github.appreciated.example;

import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = MainView.class)
public class View3 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}