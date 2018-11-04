package com.github.appreciated.example;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "view4", layout = MainAppLayout.class)
@Caption("More")
@Icon(VaadinIcon.COG)
public class View4 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}