package com.github.appreciated.app.layout.builder.impl;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AbstractViewClassAppLayoutBuilder<T extends AbstractViewClassAppLayoutBuilder> {

    protected final T instance;
    public AppLayoutConfiguration config;

    protected AbstractViewClassAppLayoutBuilder(T builder) {
        this.instance = builder;
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public T withTitle(String title) {
        config.setTitle(title);
        return instance;
    }

    /**
     * Sets the NavigationElementInfoProvider for the AppLayout.
     * The NavigationElementInfoProvider contains a algerythm to determine the icon and the caption for a View which then later on is used
     * to generate the menu entries. This allows to outsource the the information into annotations generally
     * Use in combination with add(Class<\? extends View> className)
     *
     * @param provider The Provider which contains the procedure how the icon and the caption are to be determined
     * @return
     */
    public T withNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProducer provider) {
        instance.config.setNavigationElementInfoProvider(provider);
        return instance;
    }

    /**
     * Sets the caption for the menu entries after a specific schema before initializing their views use this method
     *
     * @param interceptor The interceptor which contains the procedure how the new caption is to be determined
     * @return
     */

    public T withCaptionInterceptor(Provider<String, String> interceptor) {
        instance.config.setCaptionInterceptor(interceptor);
        return instance;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public T withDesign(AppBarDesign design) {
        instance.config.setDesign(design);
        return instance;
    }

    /**
     * This method is a shorthand to set the default navigation view for the navigator
     * Note: This will have no effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public T withDefaultNavigationView(Class<? extends View> element) {
        instance.config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return instance;
    }

    /**
     * Sets the Component provider that is being used to generate navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        instance.config.setNavigationElementProvider(provider);
        return instance;
    }

    /**
     * Sets the Component provider that is being used to generate section elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        instance.config.setSectionProvider(provider);
        return instance;
    }

    /**
     * Sets the Component provider that is being used to generate submenu navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider) {
        instance.config.setSubmenuProvider(provider);
        return instance;
    }

    /**
     * Determines that if the User navigates to another the panel which holds the content will be scrolled to the top
     *
     * @param scrollToTopOnNavigate
     * @return
     */
    public T withScrollToTopOnNavigate(boolean scrollToTopOnNavigate) {
        instance.config.setScrollToTopOnNavigate(scrollToTopOnNavigate);
        return instance;
    }

    /**
     * Determines that if the User navigates to another View submenus will be closed if the View is not inside it
     *
     * @param closeSubmenusOnNavigate
     * @return
     */
    public T withCloseSubmenusOnNavigate(boolean closeSubmenusOnNavigate) {
        instance.config.setCloseSubmenusOnNavigate(closeSubmenusOnNavigate);
        return instance;
    }

    /**
     * Appends a component to the bottom of the menu at the DEFAULT position
     *
     * @param element
     * @return
     */
    public T add(Component element) {
        return add(element, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a component to the menu to a specific position
     *
     * @param element
     * @param position
     * @return
     */
    public T add(Component element, AppLayoutConfiguration.Position position) {
        addToPosition(new ComponentNavigationElement(element), position);
        return instance;
    }

    /**
     * Appends a menu element which has a click element with a click listener to the DEFAULT position
     *
     * @param caption
     * @param icon
     * @param listener
     * @return
     */

    public T addClickable(String caption, Resource icon, Button.ClickListener listener) {
        return addClickable(caption, icon, listener, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which has a click element with a click listener at a specific position
     *
     * @param caption
     * @param icon
     * @param listener
     * @param position
     * @return
     */
    public T addClickable(String caption, Resource icon, Button.ClickListener listener, AppLayoutConfiguration.Position position) {
        addToPosition(new ClickableNavigationElement(caption, icon, listener), position);
        return instance;
    }

    /**
     * Appends one or multiple components to the menu to the DEFAULT position
     *
     * @param elements
     * @return
     */
    public T add(Component... elements) {
        instance.config.getNavigationElements().addAll(Arrays.stream(elements).map(component -> new ComponentNavigationElement(component)).collect(Collectors.toList()));
        return instance;
    }

    /**
     * Appends a section element with a caption to the menu at the DEFAULT position
     *
     * @param caption
     * @return
     */
    public T addSection(String caption) {
        instance.config.getNavigationElements().add(new SectionNavigationElement(caption));
        return instance;
    }

    /**
     * Appends a menu element which has a click element with a click listener at the DEFAULT position
     *
     * @param caption
     * @param listener
     * @return
     */
    public T addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @return
     */
    public T add(String caption, Resource icon, Class<? extends View> element) {
        return add(caption, icon, null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position which also has a badge
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        return add(caption, icon, badgeHolder, element, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param element
     * @return
     */
    public T add(String caption, Class<? extends View> element) {
        return add(caption, null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, Resource icon, Class<? extends View> element, AppLayoutConfiguration.Position position) {
        return add(caption, icon, null, element, position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at a specific position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param path
     * @param icon
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, String path, Resource icon, Class<? extends View> element, AppLayoutConfiguration.Position position) {
        return add(caption, path, icon, null, element, position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Class<? extends View> className) {
        return add(null, className, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at a specific position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Class<? extends View> className, AppLayoutConfiguration.Position position) {
        return add(null, className, position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Resource icon, Class<? extends View> className) {
        return add(icon, className, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(Resource icon, Class<? extends View> className, AppLayoutConfiguration.Position position) {
        return add(new NavigatorNavigationElement(icon, className), position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(String caption, String path, Resource icon, Class<? extends View> className) {
        return add(caption, path, icon, null, className, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        return add(caption, path, icon, badgeHolder, className, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return instance;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param path
     * @param icon
     * @param badgeHolder
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element), position);
        return instance;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @return
     */
    public T add(AbstractNavigationElement element) {
        return add(element, AppLayoutConfiguration.Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @param position
     * @return
     */
    public T add(AbstractNavigationElement element, AppLayoutConfiguration.Position position) {
        instance.config.add(element, position);
        return instance;
    }

    /**
     * Appends a Component to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(Component element) {
        instance.config.getAppBarElements().add(element);
        return instance;
    }

    /**
     * Appends one or multiple Component(s) to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(Component... element) {
        instance.config.getAppBarElements().addAll(Arrays.asList(element));
        return instance;
    }

    /**
     * Sets the Component which is shown directly behind the title label in the 'app bar' (top bar)
     *
     * @param resourceButton
     * @return
     */
    public T withAppBarIconComponent(Component resourceButton) {
        instance.config.setAppBarIconComponent(resourceButton);
        return instance;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element to a specific position
     *
     * @param element
     * @param position
     * @return
     */
    private void addToPosition(AbstractNavigationElement element, AppLayoutConfiguration.Position position) {
        instance.config.addToPosition(element, position);
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayout build() {
        return instance.config.build();
    }

}
