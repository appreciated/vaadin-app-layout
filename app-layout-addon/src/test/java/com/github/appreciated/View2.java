package com.github.appreciated;

import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = MainView.class)
public class View2 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}