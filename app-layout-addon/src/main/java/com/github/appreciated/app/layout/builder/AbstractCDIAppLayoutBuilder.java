package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class AbstractCDIAppLayoutBuilder<T extends AbstractCDIAppLayoutBuilder> extends AbstractAppLayoutBuilderBase<T> {

    protected AbstractCDIAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
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
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        return add(null, null, badgeHolder, className, AppLayoutConfiguration.Position.DEFAULT);
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
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
        return (T) this;
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
        return (T) this;
    }

}
