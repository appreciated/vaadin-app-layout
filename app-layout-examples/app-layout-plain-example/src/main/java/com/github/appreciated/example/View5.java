package com.github.appreciated.example;

import com.vaadin.flow.router.Route;

@Route(value = "view5", layout = AppLayoutWrapperView.class)
public class View5 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}