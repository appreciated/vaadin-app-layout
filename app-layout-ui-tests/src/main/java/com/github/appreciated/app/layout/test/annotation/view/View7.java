package com.github.appreciated.app.layout.test.annotation.view;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.github.appreciated.app.layout.test.annotation.AnnotationView;
import com.github.appreciated.app.layout.test.base.ExampleView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "view7", layout = AnnotationView.class)
@Caption("Gamepad")
@Icon(VaadinIcon.GAMEPAD)
public class View7 extends ExampleView {
    @Override
    protected String getViewName() {
        return getClass().getName();
    }
}
