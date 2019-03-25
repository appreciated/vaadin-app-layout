package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LeftSubmenuContainer extends VerticalLayout implements NavigationElementContainer {

    public LeftSubmenuContainer() {
        setMargin(false);
        setPadding(false);
        getElement().getThemeList().set("spacing",false);
        getElement().getThemeList().set("spacing-s",true);
    }

    @Override
    public Component getComponent() {
        return this;
    }

}
