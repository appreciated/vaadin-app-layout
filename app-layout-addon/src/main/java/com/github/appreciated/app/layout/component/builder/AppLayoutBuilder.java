package com.github.appreciated.app.layout.component.builder;

import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;

import javax.validation.constraints.NotNull;

/**
 * This is the supposed entry class to build an instance of the app-layout. The {@link AppLayoutBuilder} is a builder pattern.
 */
public class AppLayoutBuilder<T extends AppLayout> implements ComponentBuilder<T> {

    private T instance;
    private Component titleComponent;
    private Component imageComponent;
    private boolean upNavigation;
    private boolean swipeOpen = true;

    private AppLayoutBuilder(@NotNull T instance) {
        this.instance = instance;
    }

    public static <T extends AppLayout> AppLayoutBuilder<T> get(Class<T> variant) {
        try {
            return get(variant.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException(variant.getName() + " could not be instantiated, please check the logs!");
    }

    public static <T extends AppLayout> AppLayoutBuilder<T> get(T variant) {
        return new AppLayoutBuilder<>(variant);
    }

    /**
     * Creates a title {@link Component} and set it in the app-bar of the {@link AppLayout}
     *
     * @param title title as string
     * @return the builder
     */
    public AppLayoutBuilder<T> withTitle(String title) {
        setTitle(title);
        return this;
    }

    /**
     * Sets a title {@link Component} in the app-bar of the {@link AppLayout}
     *
     * @param title title as string
     */
    public void setTitle(String title) {
        Span span = new Span(title);
        span.setWidth("100%");
        span.getStyle()
                .set("margin-left", "var(--app-layout-menu-toggle-button-padding)")
                .set("white-space", "nowrap")
                .set("overflow", "hidden")
                .set("text-overflow", "ellipsis");
        setTitleComponent(span);
    }

    /**
     * Sets the {@link Component} that is supposed to represent the title in the app-bar
     *
     * @param titleComponent component that will the the title
     */
    public void setTitleComponent(Component titleComponent) {
        this.titleComponent = titleComponent;
    }

    /**
     * Sets the title component by using {@link AppLayoutBuilder#setTitleComponent(Component)}
     *
     * @param component the title component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder<T> withTitle(Component component) {
        setTitleComponent(component);
        return this;
    }

    /**
     * Builds the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return the build AppLayout instance
     */
    public T build() {
        if (titleComponent != null) {
            instance.setTitleComponent(titleComponent);
        }
        if (imageComponent != null) {
            instance.setIconComponent(imageComponent);
        }
        if (swipeOpen && instance.getDrawer() != null) {
            instance.getDrawer().getElement().setAttribute("swipe-open", true);
        }
        instance.setUpNavigationEnabled(upNavigation);
        instance.init();
        return instance;
    }

    /**
     * set the app-bar {@link Component} of the {@link AppLayout} that is built
     *
     * @param component the build app-bar component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder<T> withAppBar(Component component) {
        setAppBarComponent(component);
        return this;
    }

    public void setAppBarComponent(Component component) {
        instance.setAppBar(component);
    }

    /**
     * @param component the app menu component
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder<T> withAppMenu(Component component) {
        setAppMenu(component);
        return this;
    }

    /**
     * Sets the Component that represents the menu on the left hand / the top side (depending which {@link AppLayout} you are using).
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
    public AppLayoutBuilder<T> withIcon(String url) {
        Image image = new Image(url, "icon");
        image.setHeight("var(--app-layout-menu-button-height)");
        image.getStyle().set("margin", "var(--app-layout-space-s)");
        return withIconComponent(image);
    }

    /**
     * @param image
     * @return Itself to allow method chaining
     */
    public AppLayoutBuilder<T> withIconComponent(Component image) {
        this.imageComponent = image;
        return this;
    }

    public AppLayoutBuilder<T> withSwipeOpen(boolean swipeOpen) {
        this.swipeOpen = swipeOpen;
        return this;
    }

    public AppLayoutBuilder<T> withUpNavigation() {
        this.upNavigation = true;
        return this;
    }
}

