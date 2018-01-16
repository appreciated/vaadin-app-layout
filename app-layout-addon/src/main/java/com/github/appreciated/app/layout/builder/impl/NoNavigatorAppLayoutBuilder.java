package com.github.appreciated.app.layout.builder.impl;

import com.github.appreciated.app.layout.behaviour.AppLayout;

public class NoNavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NoNavigatorAppLayoutBuilder> {
    protected NoNavigatorAppLayoutBuilder(NoNavigatorAppLayoutBuilder builder) {
        super(builder);
    }

    public static NoNavigatorAppLayoutBuilder get(AppLayout layout) {
        return null;
    }
}
