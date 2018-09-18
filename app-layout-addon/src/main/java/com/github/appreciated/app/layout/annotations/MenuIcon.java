package com.github.appreciated.app.layout.annotations;

import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is meant to be used in combination with
 * {@link AppLayoutBuilder#withNavigationElementProvider(ComponentFactory)}
 *
 * The value of this annotation will be used for the {@link LeftNavigationComponent} as the icon of the button
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuIcon {
    VaadinIcon value();
}
