package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;

import java.util.function.Consumer;

public class NoNavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NoNavigatorAppLayoutBuilder> {
    protected NoNavigatorAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
    }

    public static NoNavigatorAppLayoutBuilder get(AppLayoutComponent layout) {
        NoNavigatorAppLayoutBuilder builder = new NoNavigatorAppLayoutBuilder(layout);
        builder.config.setNavigatorEnabled(false);
        return builder;
    }

    public NoNavigatorAppLayoutBuilder withNavigatorConsumer(Consumer<ComponentNavigator> consumer) {
        config.setComponentNavigatorConsumer(consumer);
        return this;
    }
}
