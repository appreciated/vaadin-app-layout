package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class LeftMenuComponentWrapper extends Div implements NavigationElementContainer {

    private final LeftMenuComponent menu;

    public LeftMenuComponentWrapper() {
        setSizeFull();
        menu = new LeftMenuComponent();
        super.add(menu);
        menu.setHeight("100%");
        menu.getStyle().set("padding", "var(--app-layout-menu-padding)");
        menu.getStyle().set("box-sizing", "border-box");
    }

    @Override
    public void add(Component... components) {
        menu.add(components);
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
