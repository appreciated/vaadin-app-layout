package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;

public class CDIAppLayoutBuilder extends AbstractCDIAppLayoutBuilder<CDIAppLayoutBuilder> {
    protected CDIAppLayoutBuilder(AppLayoutElementBase component) {
        super(component);
    }

    public static CDIAppLayoutBuilder get(AppLayoutElementBase layout) {
        CDIAppLayoutBuilder builder = new CDIAppLayoutBuilder(layout);
        builder.config.setCDI(true);
        return builder;
    }
}
