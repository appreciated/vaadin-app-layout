package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AppLayoutBuilder {

    private final AppLayout instance;
    private final AppLayoutConfiguration config;

    private AppLayoutBuilder(AppLayout layout) {
        instance = layout;
        config = new AppLayoutConfiguration(instance);
    }

    /**
     * Returns a Builder to build a AppLayout
     *
     * @param behaviour the behavior the AppLayout is supposed to have
     * @return
     */
    public static AppLayoutBuilder get(Behaviour behaviour) {
        return new AppLayoutBuilder(behaviour.getInstance());
    }

    /**
     * Returns a Builder to build a AppLayout
     *
     * @param layout the AppLayout instance which os supposed to be used when build is being called
     * @return
     */
    public static AppLayoutBuilder get(AppLayout layout) {
        return new AppLayoutBuilder(layout);
    }

    /**
     * Set the title which the layout is supposed to have after building
     *
     * @param title
     * @return
     */
    public AppLayoutBuilder withTitle(String title) {
        instance.setTitle(title);
        return this;
    }

    /**
     * Sets the Navigator which later on is being used by the AppLayout.
     *
     * @param navigator
     * @return
     */
    public AppLayoutBuilder withNavigatorProducer(AppLayoutConfiguration.NavigatorProducer navigator) {
        config.setNavigatorProducer(navigator);
        return this;
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
    public AppLayoutBuilder withNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProducer provider) {
        config.setNavigationElementInfoProvider(provider);
        return this;
    }

    /**
     * Sets the view names specific schema before passing them to the Navigator use this method, does
     * not work when used in combination with cdi
     *
     * @param interceptor The interceptor which contains the procedure how the new view name is to be determined
     * @return
     */

    public AppLayoutBuilder withViewNameInterceptor(Provider<String, String> interceptor) {
        config.setViewNameInterceptor(interceptor);
        return this;
    }

    /**
     * Sets the caption for the menu entries after a specific schema before initializing their views use this method
     *
     * @param interceptor The interceptor which contains the procedure how the new caption is to be determined
     * @return
     */

    public AppLayoutBuilder withCaptionInterceptor(Provider<String, String> interceptor) {
        config.setCaptionInterceptor(interceptor);
        return this;
    }

    /**
     * Set the Design which the build menu is supposed to have
     *
     * @param design
     * @return
     */
    public AppLayoutBuilder withDesign(AppBarDesign design) {
        config.setDesign(design);
        return this;
    }

    /**
     * Sets the ViewProvider of the navigator instance.
     *
     * @param supplier The ViewProvider for the navigator instance
     * @return
     */
    public AppLayoutBuilder withViewProvider(Supplier<ViewProvider> supplier) {
        config.setViewProviderSupplier(supplier);
        return this;
    }

    /**
     * Sets the Error ViewProvider of the navigator instance.
     *
     * @param supplier The consumer for the navigator instance
     * @return
     */
    public AppLayoutBuilder withErrorProvider(Supplier<ViewProvider> supplier) {
        config.setErrorProvider(supplier);
        return this;
    }

    /**
     * Sets the error View of the navigator instance
     *
     * @param supplier The consumer for the navigator instance
     * @return
     */
    public AppLayoutBuilder withErrorView(Supplier<View> supplier) {
        config.setErrorView(supplier);
        return this;
    }

    /**
     * If you need to apply some changes to navigator instance which was used by the menu.
     *
     * @param consumer The consumer for the navigator instance
     * @return
     */
    public AppLayoutBuilder withNavigatorConsumer(Consumer<Navigator> consumer) {
        config.setNavigatorConsumer(consumer);
        return this;
    }

    /**
     * This method is a shorthand to set the default navigation view for the navigator
     * It will have not effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder withDefaultNavigationView(View element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return this;
    }

    /**
     * This method is a shorthand to set the default navigation view for the navigator
     * Note: This will have no effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder withDefaultNavigationView(Class<? extends View> element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return this;
    }

    /**
     * Sets the Component provider that is being used to generate navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public AppLayoutBuilder withNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        config.setNavigationElementProvider(provider);
        return this;
    }

    /**
     * Sets the Component provider that is being used to generate section elements in the drawer.
     *
     * @param provider
     * @return
     */
    public AppLayoutBuilder withSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        config.setSectionProvider(provider);
        return this;
    }

    /**
     * Sets the Component provider that is being used to generate submenu navigation elements in the drawer.
     *
     * @param provider
     * @return
     */
    public AppLayoutBuilder withSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider) {
        config.setSubmenuProvider(provider);
        return this;
    }

    /**
     * When using any kind of CDI like Spring for Vaadin, Guice or Vaadin CDI this method needs to be called with the parameters true.
     *
     * @param cdi
     * @return
     */
    public AppLayoutBuilder withCDI(boolean cdi) {
        config.setCDI(cdi);
        return this;
    }

    /**
     * Appends a component to the bottom of the menu at the DEFAULT position
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder add(Component element) {
        return add(element, Position.DEFAULT);
    }

    /**
     * Appends a component to the menu to a specific position
     *
     * @param element
     * @param position
     * @return
     */
    public AppLayoutBuilder add(Component element, Position position) {
        addToPosition(new ComponentNavigationElement(element), position);
        return this;
    }

    /**
     * Appends a menu element which has a click element with a click listener to the DEFAULT position
     *
     * @param caption
     * @param icon
     * @param listener
     * @return
     */

    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener) {
        return addClickable(caption, icon, listener, Position.DEFAULT);
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
    public AppLayoutBuilder addClickable(String caption, Resource icon, Button.ClickListener listener, Position position) {
        addToPosition(new ClickableNavigationElement(caption, icon, listener), position);
        return this;
    }

    /**
     * Appends one or multiple components to the menu to the DEFAULT position
     *
     * @param elements
     * @return
     */
    public AppLayoutBuilder add(Component... elements) {
        config.getNavigationElements().addAll(Arrays.stream(elements).map(component -> new ComponentNavigationElement(component)).collect(Collectors.toList()));
        return this;
    }

    /**
     * Appends a section element with a caption to the menu at the DEFAULT position
     *
     * @param caption
     * @return
     */
    public AppLayoutBuilder addSection(String caption) {
        config.getNavigationElements().add(new SectionNavigationElement(caption));
        return this;
    }

    /**
     * Appends a menu element which has a click element with a click listener at the DEFAULT position
     *
     * @param caption
     * @param listener
     * @return
     */
    public AppLayoutBuilder addClickable(String caption, Button.ClickListener listener) {
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
    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element) {
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
    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        return add(caption, icon, badgeHolder, element, Position.DEFAULT);
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
    public AppLayoutBuilder add(String caption, Resource icon, View element) {
        return add(caption, icon, null, element, Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param element
     * @return
     */
    public AppLayoutBuilder add(String caption, Class<? extends View> element) {
        return add(caption, null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param element
     * @return
     */
    public AppLayoutBuilder add(String caption, View element) {
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
    public AppLayoutBuilder add(String caption, Resource icon, Class<? extends View> element, Position position) {
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
    public AppLayoutBuilder add(String caption, String path, Resource icon, Class<? extends View> element, Position position) {
        return add(caption, path, icon, null, element, position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(Class<? extends View> className) {
        return add(null, className, Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at a specific position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(Class<? extends View> className, Position position) {
        return add(null, className, position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(Resource icon, Class<? extends View> className) {
        return add(icon, className, Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(Resource icon, Class<? extends View> className, Position position) {
        return add(new NavigatorNavigationElement(icon, className), position);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(String caption, String path, Resource icon, Class<? extends View> className) {
        return add(caption, path, icon, null, className, Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public AppLayoutBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        return add(caption, path, icon, badgeHolder, className, Position.DEFAULT);
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
    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return this;
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
    public AppLayoutBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element), position);
        return this;
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
    public AppLayoutBuilder add(String caption, Resource icon, View element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, element), position);
        return this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @return
     */
    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), Position.DEFAULT);
        return this;
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
    public AppLayoutBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return this;
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
    public AppLayoutBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View element, Position position) {
        addToPosition(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element), position);
        return this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder add(AbstractNavigationElement element) {
        return add(element, Position.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param element
     * @param position
     * @return
     */
    public AppLayoutBuilder add(AbstractNavigationElement element, Position position) {
        config.add(element, position);
        return this;
    }

    /**
     * Appends a Component to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder addToAppBar(Component element) {
        config.getAppBarElements().add(element);
        return this;
    }

    /**
     * Appends one or multiple Component(s) to the 'app bar' (top bar)
     *
     * @param element
     * @return
     */
    public AppLayoutBuilder addToAppBar(Component... element) {
        config.getAppBarElements().addAll(Arrays.asList(element));
        return this;
    }

    /**
     * Sets the Component which is shown directly behind the title label in the 'app bar' (top bar)
     *
     * @param resourceButton
     * @return
     */
    public AppLayoutBuilder withAppBarIconComponent(Component resourceButton) {
        config.setAppBarIconComponent(resourceButton);
        return this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element to a specific position
     *
     * @param element
     * @param position
     * @return
     */
    private void addToPosition(AbstractNavigationElement element, Position position) {
        config.addToPosition(element, position);
    }

    /**
     * Build the layout and returns an instance of an AppLayout which also is a Component
     *
     * @return
     */
    public AppLayout build() {
        config.build();
        return instance;
    }

    public enum Position {
        HEADER, DEFAULT, FOOTER
    }

}
