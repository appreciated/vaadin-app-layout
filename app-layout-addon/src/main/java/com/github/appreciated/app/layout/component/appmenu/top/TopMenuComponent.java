package com.github.appreciated.app.layout.component.appmenu.top;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTabs;
import com.vaadin.flow.component.Component;

public class TopMenuComponent extends PaperTabs implements NavigationElementContainer {

    public TopMenuComponent() {
        setHeight("100%");
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
