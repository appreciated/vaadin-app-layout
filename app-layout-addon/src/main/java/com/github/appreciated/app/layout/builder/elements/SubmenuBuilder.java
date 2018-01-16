package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

/**
 * A Builder to build SubmenuNavigationElements
 */
public class SubmenuBuilder extends ClassSubmenuBuilder {

    private SubmenuBuilder(String title, Resource resource) {
        super(title, resource);
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
