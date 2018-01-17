package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.steps.CDIBuilderNavigatorPreamble;

public class AppLayout {

    public static CDIBuilderNavigatorPreamble getCDIBuilder(AppLayoutComponent layout) {
        return new CDIBuilderNavigatorPreamble(CDIAppLayoutBuilder.get(layout));
    }

    public static CDIBuilderNavigatorPreamble getCDIBuilder(Behaviour behaviour) {
        return new CDIBuilderNavigatorPreamble(CDIAppLayoutBuilder.get(behaviour.getInstance()));
    }

    public static NavigatorAppLayoutBuilder getDefaultBuilder(AppLayoutComponent layout) {
        return NavigatorAppLayoutBuilder.get(layout);
    }

    public static NavigatorAppLayoutBuilder getDefaultBuilder(Behaviour behaviour) {
        return NavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(AppLayoutComponent layout) {
        return NoNavigatorAppLayoutBuilder.get(layout);
    }

    public static NoNavigatorAppLayoutBuilder getNoNavigatorBuilder(Behaviour behaviour) {
        return NoNavigatorAppLayoutBuilder.get(behaviour.getInstance());
    }
}
