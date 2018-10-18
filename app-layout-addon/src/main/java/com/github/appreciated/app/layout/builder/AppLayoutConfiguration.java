package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.entity.NavigationElementInfo;
import com.vaadin.flow.component.HasElement;

import java.util.function.Function;

/**
 * Since the class AppLayoutBuilder was grew so large I decided to outsource the logic to configure an AppLayout instance into this class.
 * <p>
 * For every "Factory" you find in the class some information:
 * The following factories allow the user to exchange any {@link HasElement} that will be added to the
 * {@link AppLayoutElementBase} instance for any {@link Behaviour} or custom implementation
 */
public class AppLayoutConfiguration {


    /***** Getters and Setters *****/


    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends HasElement>, NavigationElementInfo> {
    }

}
