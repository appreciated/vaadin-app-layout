package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.impl.CDIAppLayoutBuilderWrapper;
import com.github.appreciated.app.layout.builder.impl.NavigatorAppLayoutBuilder;
import com.github.appreciated.app.layout.builder.impl.NoNavigatorAppLayoutBuilder;

public class AppLayoutBuilder {

    public static CDIAppLayoutBuilderWrapper getCDIBuilder(AppLayout layout) {
        return new CDIAppLayoutBuilderWrapper(layout);
    }

    public static CDIAppLayoutBuilderWrapper getCDIBuilder(Behaviour behaviour) {
        return new CDIAppLayoutBuilderWrapper(behaviour);
    }

    public static NavigatorAppLayoutBuilder getBuilder(AppLayout layout) {
        return NavigatorAppLayoutBuilder.get(layout);
    }

    public static NavigatorAppLayoutBuilder getBuilder(Behaviour behaviour) {
        return NavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(AppLayout layout) {
        return NoNavigatorAppLayoutBuilder.get(layout);
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(Behaviour behaviour) {
        return NoNavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }
}
