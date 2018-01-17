package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.Provider;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class AbstractViewAppLayoutBuilder<T extends AbstractViewAppLayoutBuilder> extends AbstractViewClassAppLayoutBuilder<T> {

    protected AbstractViewAppLayoutBuilder(AppLayoutComponent component) {
        super(component);
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

    public T withViewNameInterceptor(Provider<String, String> interceptor) {
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
        return add(caption, icon, null, element, AppLayoutConfiguration.Position.DEFAULT);
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
     * Appends a menu element which is bound to a view which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, Resource icon, View element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, element), position);
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
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), AppLayoutConfiguration.Position.DEFAULT);
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
     * @param position
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), position);
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
        addToPosition(new NavigatorNavigationElement(caption, viewName, null, null, element), AppLayoutConfiguration.Position.DEFAULT);
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
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, null, element), AppLayoutConfiguration.Position.DEFAULT);
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
     * @param element
     * @param position
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, View element, AppLayoutConfiguration.Position position) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, element), position);
        return (T) this;
    }

}
