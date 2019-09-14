package com.github.appreciated.app.layout.component.appbar;

import com.github.appreciated.app.layout.component.builder.ComponentBuilder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

import java.util.ArrayList;
import java.util.List;

public class AppBarBuilder implements ComponentBuilder<FlexLayout> {

    private List<Component> components = new ArrayList<>();

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
    public FlexLayout build() {
        FlexLayout layout = new FlexLayout(components.toArray(new Component[0]));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        return layout;
    }
}
