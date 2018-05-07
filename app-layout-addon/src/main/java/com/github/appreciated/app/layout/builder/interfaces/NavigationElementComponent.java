package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

/**
 * An interface that is used by many {@link HasElement} at {@link com.github.appreciated.app.layout.component}.
 * It is meant for the Components that are able to hold a caption an a icon
 */
public interface NavigationElementComponent extends HasElement {
    void setNavigationIcon(Icon resource);

    void setNavigationCaption(String string);
}
