package com.github.appreciated;

import com.vaadin.flow.router.Route;

@Route(value = "view9", layout = MainView.class)
public class View9 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}