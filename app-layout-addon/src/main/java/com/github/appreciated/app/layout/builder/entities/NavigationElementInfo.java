package com.github.appreciated.app.layout.builder.entities;

import com.vaadin.server.Resource;

import java.io.Serializable;

/**
 * Data structure that contains all the needed information of a NavigatorNavigationElement. This class is beaing used by
 * by the NavigationElementInfoProvider.
 */

public class NavigationElementInfo implements Serializable {
    String caption;
    Resource icon;
    String viewName;

    public NavigationElementInfo(String caption, Resource icon, String viewName) {
        this.caption = caption;
        this.icon = icon;
        this.viewName = viewName;
    }

    public NavigationElementInfo(String caption, String viewName) {
        this.caption = caption;
        this.viewName = viewName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Resource getIcon() {
        return icon;
    }

    public void setIcon(Resource icon) {
        this.icon = icon;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
