package com.github.appreciated.app.layout.component.appmenu;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AppSubmenu extends Composite<VerticalLayout> implements NavigationElementContainer {
    private final SubmenuContainer submenuElements;
    private final Div toggleWrapper;
    private final AppMenuIconItem item;
    private final IronIcon ironIcon;

    public AppSubmenu(String caption, Icon icon) {
        submenuElements = new SubmenuContainer();
        submenuElements.getElement().setAttribute("slot", "submenu-content");
        submenuElements.getStyle().set("border-radius", "var(--app-layout-submenuElements-button-border-radius)");

        toggleWrapper = new Div();
        toggleWrapper.getClassNames().add("app-submenuElements-item");
        toggleWrapper.getElement().setAttribute("slot", "submenu-trigger");
        toggleWrapper.getStyle().set("padding", "var(--app-layout-submenuElements-button-padding)")
                .set("margin", "var(--app-layout-submenuElements-button-margin)")
                .set("border-radius", "var(--app-layout-submenuElements-button-border-radius)");

        item = new AppMenuIconItem(caption, icon);
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle().set("fill", "var(--expand-icon-fill-color)");
        toggleWrapper.add(item, ironIcon);
    }

    public SubmenuContainer getSubmenuContainer() {
        return submenuElements;
    }

    @Override
    public Component getComponent() {
        return submenuElements;
    }
}
