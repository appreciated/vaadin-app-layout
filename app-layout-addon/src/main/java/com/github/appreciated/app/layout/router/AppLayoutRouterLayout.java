package com.github.appreciated.app.layout.router;

import com.vaadin.flow.component.page.Viewport;

/**
 * When using nested router layouts, this class should be extended by top most router layout since it defines a viewport.
 * Viewport cannot be defined on nested layouts.
 */
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout extends AppLayoutRouterLayoutBase {

}
