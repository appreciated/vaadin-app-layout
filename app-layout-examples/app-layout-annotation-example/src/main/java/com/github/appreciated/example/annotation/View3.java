package com.github.appreciated.example.annotation;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = MainAppLayout.class)
@Caption("Contact")
@Icon(VaadinIcon.CONNECT)
public class View3 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}