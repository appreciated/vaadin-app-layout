package com.github.appreciated.demo.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class View2 extends VerticalLayout implements View {
    public View2() {
        addComponent(new Label("View2"));
    }
}
