package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class AbstractViewAppLayoutBuilder<T extends AbstractViewAppLayoutBuilder> extends AbstractCDIAppLayoutBuilder<T> {

    protected AbstractViewAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
        config.setCDI(false);
    }

    /**
     * This method is a shorthand to set the default navigation view for the navigator
     * It will have not effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public T withDefaultNavigationView(View element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return (T) this;
    }

    /**
     * This method is a shorthand to set the default navigation view for the navigator
     * It will have not effect on if cdi is enabled
     *
     * @param element
     * @return
     */
    public T withDefaultNavigationView(Class<? extends View> element) {
        config.setDefaultNavigationElement(new NavigatorNavigationElement("", null, element));
        return (T) this;
    }

    /**
     * Sets the view names specific schema before passing them to the Navigator use this method, does
     * not work when used in combination with cdi
     *
     * @param interceptor The interceptor which contains the procedure how the new view name is to be determined
     * @return
     */

    public T withViewNameInterceptor(Factory<String, String> interceptor) {
        config.setViewNameInterceptor(interceptor);
        return (T) this;
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
    public T add(String caption, Resource icon, View element) {
        return add(caption, icon, null, element, Section.DEFAULT);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param element
     * @return
     */
    public T add(String caption, View element) {
        return add(caption, (Resource) null, element);
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, Resource icon, View element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, icon, element), section);
        return (T) this;
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
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), Section.DEFAULT);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), section);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param element
     * @return
     */
    public T add(String caption, String viewName, View element) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, null, null, element), Section.DEFAULT);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param icon
     * @param element
     * @return
     */
    public T add(String caption, String viewName, Resource icon, View element) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, null, element), Section.DEFAULT);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param icon
     * @param badgeHolder
     * @param view
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, view), Section.DEFAULT);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param viewName
     * @param icon
     * @param badgeHolder
     * @param view
     * @param section
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, View view, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, view), section);
        return (T) this;
    }


}
