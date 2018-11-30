package com.github.appreciated;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = MainLayout.class)
public class View2 extends AbstractView {

    public View2() {
        add(new Button("SubContent", buttonClickEvent -> UI.getCurrent().navigate(SubContent.class)));
    }

    @Override
    String getViewName() {
        return getClass().getName();
    }
}