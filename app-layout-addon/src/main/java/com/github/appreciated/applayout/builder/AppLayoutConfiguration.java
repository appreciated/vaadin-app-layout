package com.github.appreciated.applayout.builder;

import com.github.appreciated.applayout.behaviour.AppLayoutElementBase;
import com.github.appreciated.applayout.behaviour.Behaviour;
import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.github.appreciated.applayout.entity.NavigationElementInfo;
import com.vaadin.flow.component.Component;
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

    private Component menu;
    private Component appBar;
    private AppLayoutDesign design = AppLayoutDesign.DEFAULT;
    private String title;
    private Component appBarIconComponent;
    private AppLayoutElementBase instance;
    private HasElement titleComponent;

    public AppLayoutConfiguration(AppLayoutElementBase instance) {
        this.instance = instance;
    }

    public Component build() {
        if (titleComponent == null) {
            instance.setTitle(title);
        } else {
            instance.setTitleElement(titleComponent);
        }
        instance.setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        return (Component) instance;
    }


    /***** Getters and Setters *****/

    public AppLayoutDesign getDesign() {
        return design;
    }

    public void setDesign(AppLayoutDesign design) {
        this.design = design;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleComponent(HasElement titleComponent) {
        this.titleComponent = titleComponent;
    }

    public void setAppBarComponent(Component component) {

    }

    public void setAppMenu(Component component) {

    }

    @FunctionalInterface
    public interface NavigationElementInfoProducer extends Function<Class<? extends HasElement>, NavigationElementInfo> {
    }

}
