package com.github.appreciated.app.layout.component.router;

import com.github.appreciated.app.layout.component.applayout.AppLayout;

/**
 * When using nested router layouts, this class should be extended by top most router layout since it defines a viewport.
 * Viewport cannot be defined on nested layouts.
 */
//@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout<T extends AppLayout> extends AppLayoutRouterLayoutBase<T> {
    private static final long serialVersionUID = 1L;

}
