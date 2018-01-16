package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;

public class NoNavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NoNavigatorAppLayoutBuilder> {
    protected NoNavigatorAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
    }

    public static NoNavigatorAppLayoutBuilder get(AppLayoutComponent layout) {
        return new NoNavigatorAppLayoutBuilder(layout);
    }
}
