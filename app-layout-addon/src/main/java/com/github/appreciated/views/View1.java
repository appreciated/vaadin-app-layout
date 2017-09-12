package com.github.appreciated.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class View1 extends VerticalLayout implements View {
    public View1() {
        addComponent(new Label("View1"));
    }
}
