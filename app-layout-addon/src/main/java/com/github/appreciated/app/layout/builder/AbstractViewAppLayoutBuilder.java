package com.github.appreciated.app.layout.builder;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

public class AbstractViewAppLayoutBuilder<T extends AbstractViewAppLayoutBuilder> extends AbstractCDIAppLayoutBuilder<T> {

    protected AbstractViewAppLayoutBuilder(AppLayoutElementBase component) {
        super(component);
        config.setCDI(false);
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
    public T add(String caption, Icon icon, HasElement element) {
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
    public T add(String caption, HasElement element) {
        return add(caption, null, element);
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
    public T add(String caption, Icon icon, HasElement element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, icon, element), section);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a HasElement which then can be navigated to by clicking on the element at the DEFAULT position
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @return
     */
    public T add(String caption, Icon icon, DefaultBadgeHolder badgeHolder, HasElement element) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), Section.DEFAULT);
        return (T) this;
    }

    /**
     * Appends a menu element which is bound to a HasElement which then can be navigated to by clicking on the element at the DEFAULT section
     * Note: The caption, icon and navigation path will also be determined via the NavigationElementInfoProvider
     *
     * @param caption
     * @param icon
     * @param badgeHolder
     * @param element
     * @param section
     * @return
     */
    public T add(String caption, Icon icon, DefaultBadgeHolder badgeHolder, HasElement element, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, icon, badgeHolder, element), section);
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
    public T add(String caption, String viewName, Icon icon, HasElement element) {
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
     * @param hasElement
     * @return
     */
    public T add(String caption, String viewName, Icon icon, DefaultBadgeHolder badgeHolder, HasElement hasElement) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, hasElement), Section.DEFAULT);
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
     * @param hasElement
     * @param section
     * @return
     */
    public T add(String caption, String viewName, Icon icon, DefaultBadgeHolder badgeHolder, HasElement hasElement, Section section) {
        addToPosition(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, hasElement), section);
        return (T) this;
    }


}
