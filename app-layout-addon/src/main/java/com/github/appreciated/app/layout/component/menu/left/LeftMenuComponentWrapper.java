package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
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
        Arrays.stream(components).forEach(component -> {
            if (component instanceof LeftNavigationItem || component instanceof LeftClickableItem || component instanceof LeftSubmenu) {
                Div div = new Div(component);
                div.getStyle()
                        .set("padding", "0 var(--app-layout-space-s)")
                        .set("flex-shrink", "0")
                        .set("box-sizing","border-box");
                div.setWidth("100%");
                menu.add(div);
            } else {
                component.getElement().getStyle().set("flex-shrink", "0");
                menu.add(component);
            }
        });
    }

    public LeftMenu getMenu() {
        return menu;
    }
}
