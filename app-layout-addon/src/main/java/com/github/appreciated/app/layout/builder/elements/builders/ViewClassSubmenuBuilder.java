package com.github.appreciated.app.layout.builder.elements.builders;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

/**
 * A Builder to build SubmenuNavigationElements
 */
public class ViewClassSubmenuBuilder<T extends ViewClassSubmenuBuilder> extends CDISubmenuBuilder<T> {

    protected ViewClassSubmenuBuilder(String title, Resource resource) {
        super(title, resource);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static ViewClassSubmenuBuilder get(String title) {
        return new ViewClassSubmenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static ViewClassSubmenuBuilder get(Resource icon) {
        return new ViewClassSubmenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static ViewClassSubmenuBuilder get(String title, Resource icon) {
        return new ViewClassSubmenuBuilder(title, icon);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption  the caption of the menu element
     * @param viewName the url viewName under which the view will be available
     * @param element  This class name of the view
     * @return
     */
    public T add(String caption, String viewName, Resource icon, Class<? extends View> element) {
        return this.add(caption, viewName, icon, null, element);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption     the caption of the menu element
     * @param viewName    the url viewName under which the view will be available
     * @param badgeHolder
     * @param element     This class name of the view
     * @return
     */
    public T add(String caption, String viewName, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, viewName, icon, badgeHolder, element));
        return (T) this;
    }

}
