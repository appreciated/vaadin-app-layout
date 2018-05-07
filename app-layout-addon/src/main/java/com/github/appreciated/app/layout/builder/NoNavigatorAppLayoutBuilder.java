package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElement;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;

import java.util.function.Consumer;

public class NoNavigatorAppLayoutBuilder extends AbstractViewAppLayoutBuilder<NoNavigatorAppLayoutBuilder> {
    protected NoNavigatorAppLayoutBuilder(AppLayoutElement component) {
        super(component);
    }

    public static NoNavigatorAppLayoutBuilder get(AppLayoutElement layout) {
        NoNavigatorAppLayoutBuilder builder = new NoNavigatorAppLayoutBuilder(layout);
        builder.config.setNavigatorEnabled(false);
        return builder;
    }

    public NoNavigatorAppLayoutBuilder withNavigatorConsumer(Consumer<ComponentNavigator> consumer) {
        config.setComponentNavigatorConsumer(consumer);
        return this;
    }
}
