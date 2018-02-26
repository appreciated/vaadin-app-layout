package com.github.appreciated.app.layout.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * FIXME please use @link notation for references used in JavaDoc. Here for AppLayoutBuilder. This has three advantages:
 * - the content in links are renamed as well when you use refactoring options from your IDE => it keeps the link synchronized
 * - it is navigateable when you hover over a class or method to read the JavaDoc
 * - you can use CodeCompleetion within the link annotation => more convenient writing of JavaDoc
 * 
 * PS: referencing a method use a sharp
 * e.g. {@link MenuIcon#value()}
 * 
 * This is true for all JavaDoc.
 * 
 * This annotation is meant to be used in combination with {@link AppLayoutBuilder#add()} AppLayoutBuilder::add(Class<\? extends View>
 * className) and AppLayoutBuilder withNavigationElementInfoProvider(...)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuCaption {
    String value();
}
