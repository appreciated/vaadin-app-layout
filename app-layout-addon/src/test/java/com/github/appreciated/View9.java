package com.github.appreciated;

import com.vaadin.flow.router.Route;

@Route(value = "view9", layout = MainLayout.class)
public class View9 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}