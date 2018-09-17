package com.github.appreciated.example;

import com.github.appreciated.applayout.annotations.Caption;
import com.github.appreciated.applayout.annotations.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = MainView.class)
@Caption("Contact")
@Icon(VaadinIcon.CONNECT)
public class View3 extends AbstractView {
    @Override
    String getViewName() {
        return getClass().getName();
    }
}