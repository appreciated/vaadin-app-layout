package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

/**
 * Contains all  Class<View> related functions that are not usable with CDI
 *
 * @param <T>
 */
public class AbstractViewClassAppLayoutBuilder<T extends AbstractViewClassAppLayoutBuilder> extends AbstractCDIAppLayoutBuilder<T> {

    protected AbstractViewClassAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
        config.setCDI(false);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at a specific section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param icon
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, String viewName, Resource icon, Class<? extends View> element, Section section) {
        return add(caption, viewName, icon, null, element, section);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(String caption, String viewName, Resource icon, Class<? extends View> className) {
        return add(caption, viewName, icon, null, className, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param className
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        return add(caption, viewName, icon, badgeHolder, className, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param icon
     * @param badgeHolder
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, element), section);
        return (T) this;
    }

}
