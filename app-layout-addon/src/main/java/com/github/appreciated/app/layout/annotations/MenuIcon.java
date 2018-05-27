package com.github.appreciated.app.layout.annotations;

import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.vaadin.flow.component.icon.VaadinIcons;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is meant to be used in combination with
 * {@link com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder#add(Class)}
 * which is also inherited by {@link com.github.appreciated.app.layout.builder.NavigatorAppLayoutBuilder} and
 * {@link com.github.appreciated.app.layout.builder.NoNavigatorAppLayoutBuilder} and also in combination with
 * {@link com.github.appreciated.app.layout.builder.AbstractAppLayoutBuilderBase#withNavigationElementProvider(ComponentFactory)}
 * which is also inherited by all Builders
 *
 * The Value of this annotation will be used for the {@link com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement} as the icon of the button
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuIcon {
    VaadinIcons value();
}
