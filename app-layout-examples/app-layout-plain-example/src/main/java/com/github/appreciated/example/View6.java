package com.github.appreciated.example;

import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = AppLayoutWrapperView.class)
public class View6 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}