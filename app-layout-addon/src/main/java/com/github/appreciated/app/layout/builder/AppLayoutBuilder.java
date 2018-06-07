package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Behaviour;

public class AppLayoutBuilder extends AbstractCDIAppLayoutBuilder<AppLayoutBuilder> {
    protected AppLayoutBuilder(AppLayoutElementBase component) {
        super(component);
    }

    public static AppLayoutBuilder get(AppLayoutElementBase layout) {
        AppLayoutBuilder builder = new AppLayoutBuilder(layout);
        return builder;
    }

    public static AppLayoutBuilder get(Behaviour layout) {
        return get(layout.getInstance());
    }

}

