package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.Arrays;

public class LeftMenuComponentWrapper extends Div implements NavigationElementContainer {

    private final LeftMenuComponent menu;

    public LeftMenuComponentWrapper() {
        setSizeFull();
        menu = new LeftMenuComponent();
        super.add(menu);
        menu.setHeight("100%");
        getStyle().set("padding", "0");
        menu.getStyle().set("padding", "0");
    }

    @Override
    public void add(Component... components) {
        Arrays.stream(components).forEach(component -> component.getElement().getStyle().set("flex-shrink", "0"));
        menu.add(components);
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
