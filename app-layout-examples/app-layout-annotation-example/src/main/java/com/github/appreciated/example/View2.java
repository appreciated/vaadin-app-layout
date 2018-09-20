package com.github.appreciated.example;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.annotations.Icon;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = MainView.class)
@Caption("Charts")
@Icon(VaadinIcon.SPLINE_CHART)
public class View2 extends AbstractView {

    public View2() {
        add(new Button("SubContent", buttonClickEvent -> UI.getCurrent().navigate(SubContent.class)));
    }

    @Override
    String getViewName() {
        return getClass().getName();
    }
}