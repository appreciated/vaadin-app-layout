package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.Arrays;

public class LeftMenuComponentWrapper extends Div implements NavigationElementContainer {

    private final LeftMenu menu;

    public LeftMenuComponentWrapper() {
        setSizeFull();
        menu = new LeftMenu();
        super.add(menu);
        setHeight("100%");
        setWidth("100%");
        menu.setHeight("100%");
        menu.getStyle().set("overflow", "auto");
        //menu.getStyle().set("padding", "var(--app-layout-menu-padding)");
    }

    @Override
    public void add(Component... components) {
        Arrays.stream(components).forEach(component -> component.getElement().getStyle().set("flex-shrink", "0"));
        menu.add(components);
    }

    public LeftMenu getMenu() {
        return menu;
    }
}
