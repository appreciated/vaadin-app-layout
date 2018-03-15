package com.github.appreciated.app.layout.builder.interfaces;

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

/**
 * An interface that is used by many {@link Component} at {@link com.github.appreciated.app.layout.component}.
 * It is meant for the Components that are able to hold a caption an a icon
 */
public interface NavigationElementComponent extends Component {
    void setNavigationIcon(Resource resource);

    void setNavigationCaption(String string);
}
