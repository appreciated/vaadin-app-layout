package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.interfaces.Provider;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AbstractAppLayoutBuilderBase<T extends AbstractAppLayoutBuilderBase> {

    protected AppLayoutConfiguration config;

    protected AbstractAppLayoutBuilderBase(AppLayoutComponent component) {
        config = new AppLayoutConfiguration(component);
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public T withTitle(String title) {
        config.setTitle(title);
        return (T) this;
    }

    /**
     * Replaces the title component with another component
     *
     * @param component
     * @return
     */
    public T withTitle(Component component) {
        config.setTitleComponent(component);
        return (T) this;
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
        this.config.setNavigationElementInfoProvider(provider);
        return (T) this;
    }

    /**
     * Sets the caption for the menu entries after a specific schema before initializing their views use this method
     *
     * @param interceptor The interceptor which contains the procedure how the new caption is to be determined
     * @return
     */

    public T withCaptionInterceptor(Provider<String, String> interceptor) {
        config.setCaptionInterceptor(interceptor);
        return (T) this;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public T withDesign(AppBarDesign design) {
        config.setDesign(design);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        config.setNavigationElementProvider(provider);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate section elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        config.setSectionProvider(provider);
        return (T) this;
    }

    /**
     * Sets the Component provider that is being used to generate submenu navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public T withSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider) {
        config.setSubmenuProvider(provider);
        return (T) this;
    }

    /**
     * Determines that if the User navigates to another the panel which holds the content will be scrolled to the top
     *
     * @param scrollToTopOnNavigate
     * @return
     */
    public T withScrollToTopOnNavigate(boolean scrollToTopOnNavigate) {
        config.setScrollToTopOnNavigate(scrollToTopOnNavigate);
        return (T) this;
    }

    /**
     * Determines that if the User navigates to another View submenus will be closed if the View is not inside it
     *
     * @param closeSubmenusOnNavigate
     * @return
     */
    public T withCloseSubmenusOnNavigate(boolean closeSubmenusOnNavigate) {
        config.setCloseSubmenusOnNavigate(closeSubmenusOnNavigate);
        return (T) this;
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
        return (T) this;
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
        return (T) this;
    }

    /**
     * Appends one or multiple components to the menu to the DEFAULT position
     *
     * @param elements
     * @return
     */
    public T add(Component... elements) {
        config.getNavigationElements().addAll(Arrays.stream(elements).map(component -> new ComponentNavigationElement(component)).collect(Collectors.toList()));
        return (T) this;
    }

    /**
     * Appends a section element with a caption to the menu at the DEFAULT position
     *
     * @param caption
     * @return
     */
    public T addSection(String caption) {
        config.getNavigationElements().add(new SectionNavigationElement(caption));
        return (T) this;
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
        config.add(element, position);
        return (T) this;
    }

    /**
     * Appends a Component to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(Component element) {
        config.getAppBarElements().add(element);
        return (T) this;
    }

    /**
     * Appends one or multiple Component(s) to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public T addToAppBar(Component... element) {
        config.getAppBarElements().addAll(Arrays.asList(element));
        return (T) this;
    }

    /**
     * Sets the Component which is shown directly behind the title label in the 'app bar' (top bar)
     *
     * @param resourceButton
     * @return
     */
    public T withAppBarIconComponent(Component resourceButton) {
        config.setAppBarIconComponent(resourceButton);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element to a specific position
     *
     * @param element
     * @param position
     * @return
     */
    protected void addToPosition(AbstractNavigationElement element, AppLayoutConfiguration.Position position) {
        config.addToPosition(element, position);
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayoutComponent build() {
        return config.build();
    }

}
