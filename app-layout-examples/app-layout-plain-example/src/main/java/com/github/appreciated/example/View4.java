package com.github.appreciated.example;

import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = AppLayoutWrapperView.class)
public class View4 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}