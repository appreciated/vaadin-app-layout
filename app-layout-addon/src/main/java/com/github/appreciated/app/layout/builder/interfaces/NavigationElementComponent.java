package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasElement;

/**
 * An interface that is used by many {@link HasElement} at {@link com.github.appreciated.app.layout.component}.
 * It is meant for the Components that are able to hold a caption an a icon
 */
public interface NavigationElementComponent extends HasElement {
    void setNavigationIcon(String resource);

    void setNavigationCaption(String string);

    void setClickListener(ComponentEventListener listener);

}
