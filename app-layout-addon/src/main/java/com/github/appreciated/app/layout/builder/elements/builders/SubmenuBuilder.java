package com.github.appreciated.app.layout.builder.elements.builders;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;


/**
 * A Builder to build {@link SubmenuNavigationElement} this buider is meant to be used in combination with the {@link com.github.appreciated.app.layout.builder.NavigatorAppLayoutBuilder}
 * and the {@link com.github.appreciated.app.layout.builder.NoNavigatorAppLayoutBuilder}
 */
public class SubmenuBuilder extends ViewClassSubmenuBuilder<SubmenuBuilder> {

    private SubmenuBuilder(String title, Resource resource) {
        super(title, resource);
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

    public SubmenuBuilder add(String caption, String path, Resource icon, View element) {
        return this.add(caption, path, icon, null, element);
    }

    public SubmenuBuilder add(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View element) {
        this.add(new NavigatorNavigationElement(caption, path, icon, badgeHolder, element));
        return this;
    }
}
