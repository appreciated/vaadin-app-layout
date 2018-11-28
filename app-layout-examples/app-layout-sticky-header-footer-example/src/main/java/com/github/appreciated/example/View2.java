package com.github.appreciated.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.Route;

@Route(value = "view2", layout = MainAppLayout.class)
public class View2 extends AbstractView {

    public View2() {
        // Navigate to content that is not accessible from the menu directly. And see the results in the UI
        add(new Button("SubContent", buttonClickEvent -> UI.getCurrent().navigate(SubContent.class)));
    }

    @Override
    String getViewName() {
        return getClass().getName();
    }
}