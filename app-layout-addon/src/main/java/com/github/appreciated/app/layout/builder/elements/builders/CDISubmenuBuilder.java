package com.github.appreciated.app.layout.builder.elements.builders;

import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A Builder to build {@link SubmenuNavigationElement} this buider is meant to be used in combination with the {@link com.github.appreciated.app.layout.builder.CDIAppLayoutBuilder}
 */
public class CDISubmenuBuilder<T extends CDISubmenuBuilder> {

    final String title;
    final Resource resource;
    List<AbstractNavigationElement> submenuElements = new ArrayList<>();

    protected CDISubmenuBuilder(String title, Resource resource) {
        this.title = title;
        this.resource = resource;
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static CDISubmenuBuilder get(String title) {
        return new CDISubmenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static CDISubmenuBuilder get(Resource icon) {
        return new CDISubmenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static CDISubmenuBuilder get(String title, Resource icon) {
        return new CDISubmenuBuilder(title, icon);
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param className This class name of the view
     * @return
     */
    public T add(Class<? extends View> className) {
        return add((Resource) null, className);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param className This class name of the view
     * @return
     */
    public T add(Resource icon, Class<? extends View> className) {
        return add(new NavigatorNavigationElement(icon, className));
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public T add(String caption, Class<? extends View> element) {
        return this.add(caption, null, element);
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public T add(String caption, Resource icon, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, element));
        return (T) this;
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public T add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, badgeHolder, element));
        return (T) this;
    }

    public T addClickable(Resource icon, Button.ClickListener listener) {
        return addClickable(null, icon, listener);
    }

    public T addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    public T addClickable(String caption, Resource icon, Button.ClickListener listener) {
        this.submenuElements.add(new ClickableNavigationElement(caption, icon, listener));
        return (T) this;
    }

    public T add(Component element) {
        this.submenuElements.add(new ComponentNavigationElement(element));
        return (T) this;
    }

    public T add(AbstractNavigationElement element) {
        this.submenuElements.add(element);
        return (T) this;
    }

    public SubmenuNavigationElement build() {
        return new SubmenuNavigationElement(title, resource, submenuElements);
    }
}
