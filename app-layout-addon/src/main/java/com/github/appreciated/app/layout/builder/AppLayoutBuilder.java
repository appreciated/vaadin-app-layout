package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;

/**
 * This is the supposed entry class to build an instance of the app-layout. The {@link AppLayoutBuilder} is a builder pattern.
 */
public class AppLayoutBuilder implements ComponentBuilder {

    private AppLayout instance;
    private Component titleComponent;
    private Component imageComponent;
    private boolean upNavigation;
    private boolean swipeOpen = true;

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
     * Creates a title {@link Component} and set it in the app-bar of the {@link AppLayout}
     *
     * @param title
     * @return
     */
    public AppLayoutBuilder withTitle(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets a title {@link Component} in the app-bar of the {@link AppLayout}
     *
     * @param title
     */
    public void setTitle(String title) {
        Span span = new Span(title);
        span.setWidth("100%");
        span.getStyle()
                .set("white-space", "nowrap")
                .set("overflow", "hidden")
                .set("text-overflow", "ellipsis");
        setTitleComponent(span);
    }

    /**
     * Sets the {@link Component} that is supposed to represent the title in the app-bar
     *
     * @param titleComponent
     */
    public void setTitleComponent(Component titleComponent) {
        this.titleComponent = titleComponent;
    }

    /**
     * Sets the title component by using {@link AppLayoutBuilder#setTitleComponent(Component)}
     *
     * @param component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder withTitle(Component component) {
        setTitleComponent(component);
        return this;
    }

    /**
     * Builds the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayout build() {
        if (titleComponent != null) {
            instance.setTitleComponent(titleComponent);
        }
        if (imageComponent != null) {
            instance.setIconComponent(imageComponent);
        }
        if (swipeOpen) {
            instance.getDrawer().getElement().setAttribute("swipe-open",true);
        }
        instance.setUpNavigationEnabled(upNavigation);
        instance.init();
        return instance;

    }

    /**
     * set the app-bar {@link Component} of the {@link AppLayout} that is built
     *
     * @param component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder withAppBar(Component component) {
        setAppBarComponent(component);
        return this;
    }

    public void setAppBarComponent(Component component) {
        instance.setAppBar(component);
    }

    /**
     * @param component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder withAppMenu(Component component) {
        setAppMenu(component);
        return this;
    }

    /**
     * Sets the Component that represents the menu on the left hand / the top side (depending which {@link Behaviour} you are using).
     *
     * @param component
     */
    public void setAppMenu(Component component) {
        instance.setAppMenu(component);
    }

    /**
     * @param url a url to the image that is supposed to be shown in the app bar
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder withIcon(String url) {
        Image image = new Image(url, "icon");
        image.setHeight("var(--app-layout-menu-button-height)");
        image.getStyle().set("margin","calc(calc(var(--app-layout-bar-height) - var(--app-layout-menu-button-height)) / 2)");
        return withIconComponent(image);
    }


    public AppLayoutBuilder withSwipeOpen(boolean swipeOpen) {
        this.swipeOpen = swipeOpen;
        return this;
    }

    /**
     * @param image
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder withIconComponent(Component image) {
        this.imageComponent = image;
        return this;
    }

    public AppLayoutBuilder withUpNavigation() {
        this.upNavigation = true;
        return this;
    }
}

