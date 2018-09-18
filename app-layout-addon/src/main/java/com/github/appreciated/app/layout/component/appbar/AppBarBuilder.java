package com.github.appreciated.app.layout.component.appbar;

import com.github.appreciated.app.layout.builder.ComponentBuilder;
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

    public AppBarBuilder add(Component component) {
        components.add(component);
        return this;
    }

    @Override
    public Component build() {
        return new HorizontalLayout(components.toArray(new Component[components.size()]));
    }
}
