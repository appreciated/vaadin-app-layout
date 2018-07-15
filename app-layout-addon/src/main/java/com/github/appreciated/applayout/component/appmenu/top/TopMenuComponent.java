package com.github.appreciated.applayout.component.appmenu.top;

import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.webcomponents.papertabs.PaperTabs;
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
