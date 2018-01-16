package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;

public class AppLayout {

    public static CDIAppLayoutBuilderWrapper getCDIBuilder(AppLayoutComponent layout) {
        return new CDIAppLayoutBuilderWrapper(layout);
    }

    public static CDIAppLayoutBuilderWrapper getCDIBuilder(Behaviour behaviour) {
        return new CDIAppLayoutBuilderWrapper(behaviour);
    }

    public static NavigatorAppLayoutBuilder getBuilder(AppLayoutComponent layout) {
        return NavigatorAppLayoutBuilder.get(layout);
    }

    public static NavigatorAppLayoutBuilder getBuilder(Behaviour behaviour) {
        return NavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(AppLayoutComponent layout) {
        return NoNavigatorAppLayoutBuilder.get(layout);
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(Behaviour behaviour) {
        return NoNavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }
}
