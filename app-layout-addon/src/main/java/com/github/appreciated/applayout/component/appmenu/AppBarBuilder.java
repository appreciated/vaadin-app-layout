package com.github.appreciated.applayout.component.appmenu;

import com.github.appreciated.applayout.builder.ComponentBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.ArrayList;

public class AppBarBuilder implements ComponentBuilder {

    ArrayList<Component> components = new ArrayList<>();

    protected AppBarBuilder() {
    }

    public static AppBarBuilder get() {
        return new AppBarBuilder();
    }

    @Override
    public Component build() {
        return new HorizontalLayout(components.toArray(new Component[components.size()]));
    }

    public AppBarBuilder withElement(Component component) {
        return this;
    }
}
