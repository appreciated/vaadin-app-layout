package com.github.appreciated.applayout.builder;

import com.github.appreciated.applayout.behaviour.AppLayout;
import com.github.appreciated.applayout.behaviour.Behaviour;
import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

public class AppLayoutBuilder implements ComponentBuilder {

    private Component menu;
    private Component appBar;
    private AppLayoutDesign design = AppLayoutDesign.DEFAULT;
    private String title;
    private Component appBarIconComponent;
    private AppLayout instance;
    private HasElement titleComponent;

    private AppLayoutBuilder(AppLayout instance) {
        this.instance = instance;
    }

    public static AppLayoutBuilder get(Behaviour variant) {
        return new AppLayoutBuilder(variant.getInstance());
    }

    public static AppLayoutBuilder get(AppLayout variant) {
        return new AppLayoutBuilder(variant);
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public AppLayoutBuilder withTitle(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Replaces the title component with another component
     *
     * @param component
     * @return
     */
    public AppLayoutBuilder withTitle(Component component) {
        setTitleComponent(component);
        return this;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public AppLayoutBuilder withDesign(AppLayoutDesign design) {
        setDesign(design);
        return this;
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayout build() {
        if (titleComponent == null) {
            instance.setTitle(title);
        } else {
            instance.setTitleElement(titleComponent);
        }
        instance.setDesign(design);
        if (appBarIconComponent != null) {
            instance.addAppBarIcon(appBarIconComponent);
        }
        return instance;

    }

    public AppLayoutBuilder withAppBar(Component component) {
        setAppBarComponent(component);
        return this;
    }

    public AppLayoutBuilder withAppMenu(NavigationElementContainer component) {
        setAppMenu(component);
        return this;
    }

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
        instance.setAppBar(component);
    }

    public void setAppMenu(NavigationElementContainer component) {
        instance.setAppMenu(component);
    }


}

