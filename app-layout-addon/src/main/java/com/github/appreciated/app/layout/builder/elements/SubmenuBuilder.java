package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A Builder to build SubmenuNavigationElements
 */
public class SubmenuBuilder {

    private final String title;
    private final Resource resource;
    private List<AbstractNavigationElement> submenuElements = new ArrayList<>();

    private SubmenuBuilder(String title, Resource resource) {
        this.title = title;
        this.resource = resource;
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has a title
     *
     * @param title
     * @return
     */
    public static SubmenuBuilder get(String title) {
        return new SubmenuBuilder(title, null);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that only has an icon
     *
     * @param icon
     * @return
     */
    public static SubmenuBuilder get(Resource icon) {
        return new SubmenuBuilder(null, icon);
    }

    /**
     * returns a SubmenuBuilder with a predefined expanding element that has an icon and a title
     *
     * @param icon
     * @return
     */
    public static SubmenuBuilder get(String title, Resource icon) {
        return new SubmenuBuilder(title, icon);
    }

    /**
     * Adds a MenuElement that has a title and shows a badge showing a number
     *
     * @param caption
     * @param badgeHolder
     * @return
     */
    public SubmenuBuilder add(String caption, DefaultBadgeHolder badgeHolder) {
        return add(caption, null, badgeHolder, (View) null);
    }

    /**
     * Adds a MenuElement that has a title and an icon and also shows a badge showing a number
     *
     * @param caption
     * @param badgeHolder
     * @return
     */
    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder) {
        return add(caption, icon, badgeHolder, (View) null);
    }

    /**
     * Adds a MenuElement the shows a caption and that if clicked navigates to the View passed as parameter
     *
     * @param caption
     * @param element
     * @return
     */
    public SubmenuBuilder add(String caption, View element) {
        return add(caption, null, (DefaultBadgeHolder) null, element);
    }

    /**
     * Adds a MenuElement the shows a caption and a icon and that if clicked navigates to the View passed as parameter
     *
     * @param caption
     * @param element
     * @return
     */
    public SubmenuBuilder add(String caption, Resource icon, View element) {
        return add(caption, icon, null, element);
    }

    /**
     * Adds a MenuElement the shows a caption and a icon. Also it shows a badge containing a number. If clicked navigates to the View passed as parameter
     *
     * @param caption
     * @param element
     * @return
     */
    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        this.add(new NavigatorNavigationElement(caption, icon, badgeHolder, element));
        return this;
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param className This class name of the view
     * @return
     */
    public SubmenuBuilder add(Class<? extends View> className) {
        return add((Resource) null, className);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param className This class name of the view
     * @return
     */
    public SubmenuBuilder add(Resource icon, Class<? extends View> className) {
        return add(new NavigatorNavigationElement(icon, className));
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public SubmenuBuilder add(String caption, Class<? extends View> element) {
        return this.add(caption, null, element);
    }

    /**
     * Adds a MenuElement the may show a caption and/or an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public SubmenuBuilder add(String caption, Resource icon, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, element));
        return this;
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param element This class name of the view
     * @return
     */
    public SubmenuBuilder add(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, icon, badgeHolder, element));
        return this;
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption the caption of the menu element
     * @param path    the url path under which the view will be available
     * @param element This class name of the view
     * @return
     */
    public SubmenuBuilder add(String caption, String path, Resource icon, Class<? extends View> element) {
        return this.add(caption, path, icon, null, element);
    }

    /**
     * Adds a MenuElement the may show a caption and an icon. If clicked the View passed as parameter will be navigated to.
     *
     * @param caption     the caption of the menu element
     * @param path        the url path under which the view will be available
     * @param badgeHolder
     * @param element     This class name of the view
     * @return
     */
    public SubmenuBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> element) {
        this.add(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, View element) {
        return this.add(caption, path, icon, null, element);
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        this.add(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element));
        return this;
    }

    public SubmenuBuilder addClickable(Resource icon, Button.ClickListener listener) {
        return addClickable(null, icon, listener);
    }

    public SubmenuBuilder addClickable(String caption, Button.ClickListener listener) {
        return addClickable(caption, null, listener);
    }

    public SubmenuBuilder addClickable(String caption, Resource icon, Button.ClickListener listener) {
        this.submenuElements.add(new ClickableNavigationElement(caption, icon, listener));
        return this;
    }

    public SubmenuBuilder add(Component element) {
        this.submenuElements.add(new ComponentNavigationElement(element));
        return this;
    }

    public SubmenuBuilder add(AbstractNavigationElement element) {
        this.submenuElements.add(element);
        return this;
    }

    public SubmenuNavigationElement build() {
        return new SubmenuNavigationElement(title, resource, submenuElements);
    }
}
