package com.github.appreciated;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
public class View1 extends VerticalLayout {

    public View1() {
        add(new Label("............................................................"));
        setSizeFull();
    }

}