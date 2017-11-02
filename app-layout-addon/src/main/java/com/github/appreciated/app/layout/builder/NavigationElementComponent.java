package com.github.appreciated.app.layout.builder;

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

public interface NavigationElementComponent extends Component {
    void setNavigationIcon(Resource resource);

    void setNavigationCaption(String string);
}
