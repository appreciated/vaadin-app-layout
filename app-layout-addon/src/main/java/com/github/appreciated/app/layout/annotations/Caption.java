package com.github.appreciated.app.layout.annotations;

import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * When using this the {@link Caption} Annotation with a {@link com.vaadin.flow.component.Component} with a {@link com.vaadin.flow.router.Route} Annotation
 * the value of this annotation will be used for the {@link LeftNavigationItem} as caption of the button
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Caption {
    String value();

}
