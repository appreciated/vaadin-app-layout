package com.github.appreciated.app.layout.component.builder;

import com.github.appreciated.app.layout.component.applayout.AppLayoutElementBase;
import com.github.appreciated.app.layout.entity.NavigationElementInfo;
import com.vaadin.flow.component.HasElement;

import java.util.function.Function;

/**
 * Since the class AppLayoutBuilder was grew so large I decided to outsource the logic to configure an AppLayout instance into this class.
 * <p>
 * For every "Factory" you find in the class some information:
 * The following factories allow the user to exchange any {@link HasElement} that will be added to the
 * {@link AppLayoutElementBase} instance for any {@link com.github.appreciated.app.layout.component.applayout.AppLayout} or custom implementation
 */
public class AppLayoutConfiguration {


    /***** Getters and Setters *****/


    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends HasElement>, NavigationElementInfo> {
    }

}
