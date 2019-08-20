package com.github.appreciated.app.layout.test.base;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class ExampleView extends HorizontalLayout {
    public ExampleView() {
        setSizeFull();
        Label label = new Label("< " + getViewName() + " >");
        add(label);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

    protected abstract String getViewName();
}