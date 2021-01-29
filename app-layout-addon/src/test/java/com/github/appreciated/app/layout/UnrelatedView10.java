package com.github.appreciated.app.layout;

import com.vaadin.flow.router.Route;

@Route(value = "view10", layout = MainLayout.class)
public class UnrelatedView10 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}
