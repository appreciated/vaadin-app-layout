package com.github.appreciated.applayout.builder;

import com.github.appreciated.applayout.behaviour.AppLayoutElementBase;
import com.github.appreciated.applayout.behaviour.Behaviour;
import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.vaadin.flow.component.Component;

public class AppLayoutBuilder implements ComponentBuilder {

    protected AppLayoutConfiguration config;

    protected AppLayoutBuilder(AppLayoutElementBase component) {
        config = new AppLayoutConfiguration(component);
    }

    public static AppLayoutBuilder get(Behaviour variant) {
        return new AppLayoutBuilder(variant.getInstance());
    }

    public static AppLayoutBuilder get(AppLayoutElementBase variant) {
        return new AppLayoutBuilder(variant);
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public AppLayoutBuilder withTitle(String title) {
        config.setTitle(title);
        return this;
    }

    /**
     * Replaces the title component with another component
     *
     * @param component
     * @return
     */
    public AppLayoutBuilder withTitle(Component component) {
        config.setTitleComponent(component);
        return this;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public AppLayoutBuilder withDesign(AppLayoutDesign design) {
        config.setDesign(design);
        return this;
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public Component build() {
        return config.build();
    }

    public AppLayoutBuilder withAppBar(Component component) {
        config.setAppBarComponent(component);
        return this;
    }

    public AppLayoutBuilder withAppMenu(Component component) {
        config.setAppMenu(component);
        return this;
    }

}

