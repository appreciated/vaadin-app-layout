package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.app.layout.component.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LeftSubmenuContainer extends VerticalLayout implements NavigationElementContainer {
    private static final long serialVersionUID = 1L;

    public LeftSubmenuContainer() {
        setMargin(false);
        setPadding(false);
        getElement().getThemeList().set("spacing",false);
        getElement().getThemeList().set("spacing-s",true);
    }
}
