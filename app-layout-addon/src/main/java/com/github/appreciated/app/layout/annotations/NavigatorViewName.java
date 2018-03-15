package com.github.appreciated.app.layout.annotations;

import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is meant to be used in combination with {@link com.github.appreciated.app.layout.builder.NavigatorAppLayoutBuilder} and
 * {@link com.github.appreciated.app.layout.builder.NoNavigatorAppLayoutBuilder} and also in combination with
 * {@link com.github.appreciated.app.layout.builder.AbstractAppLayoutBuilderBase#withNavigationElementProvider(ComponentFactory)}
 * which is also inherited by all Builders
 *
 * The Value of this annotation will be used for the {@link com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement}
 * as the viewname that will be passed to the {@link com.vaadin.navigator.Navigator} or the
 * {@link com.github.appreciated.app.layout.navigator.ComponentNavigator} instance of the button
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NavigatorViewName {
    String value();
}
