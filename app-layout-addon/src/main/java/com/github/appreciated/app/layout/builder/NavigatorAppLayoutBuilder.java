package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;

public class NavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NavigatorAppLayoutBuilder> {
    protected NavigatorAppLayoutBuilder(AppLayoutElementBase component) {
        super(component);
    }

    public static NavigatorAppLayoutBuilder get(AppLayoutElementBase layout) {
        NavigatorAppLayoutBuilder builder = new NavigatorAppLayoutBuilder(layout);
        return builder;
    }

}

