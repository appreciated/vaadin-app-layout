package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;

public class LeftSubmenuContainer extends VerticalLayout implements NavigationElementContainer {

    public LeftSubmenuContainer() {
        setMargin(false);
        setPadding(false);
        getElement().getThemeList().set("spacing",false);
        getElement().getThemeList().set("spacing-s",true);
    }
}
