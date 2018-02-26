package com.github.appreciated.app.layout.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is meant to be used in combination with AppLayoutBuilder::add(Class<\? extends View> className) and
 * AppLayoutBuilder withNavigationElementInfoProvider(...) but not in combination with CDI
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface NavigatorViewName {
    String value();
}
