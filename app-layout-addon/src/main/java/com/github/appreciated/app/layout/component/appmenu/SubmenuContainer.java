package com.github.appreciated.app.layout.component.appmenu;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SubmenuContainer extends VerticalLayout implements NavigationElementContainer {
    @Override
    public Component getComponent() {
        return this;
    }

}
