package com.github.appreciated.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class View3 extends VerticalLayout implements View {
    public View3() {
        addComponent(new Label("View3"));
    }
}
