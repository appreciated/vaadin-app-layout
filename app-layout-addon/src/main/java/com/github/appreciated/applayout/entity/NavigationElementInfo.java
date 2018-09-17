package com.github.appreciated.applayout.entity;

import com.vaadin.flow.component.icon.Icon;

/**
 * Data structure that contains all the needed information of a {@link com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement}. This class is being used by
 * by the {@link DefaultNavigationElementInfoProducer}.
 */

public class NavigationElementInfo {
    String caption;
    Icon icon;
    String route;

    public NavigationElementInfo(String caption, Icon icon, String route) {
        this.caption = caption;
        this.icon = icon;
        this.route = route;
    }

    public NavigationElementInfo(String caption, String route) {
        this.caption = caption;
        this.route = route;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
