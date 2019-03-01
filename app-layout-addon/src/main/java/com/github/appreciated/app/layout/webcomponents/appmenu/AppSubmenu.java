package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AppSubmenu extends VerticalLayout {
    private final AppMenu menu;
    private final Div toggleWrapper;
    private final AppMenuIconItem item;
    private final IronIcon ironIcon;
    private AppMenu parent;

    public AppSubmenu(String sectionName, Icon icon) {
        menu = new AppMenu();
        menu.getElement().setAttribute("slot", "submenu-content");
        menu.getStyle()
                .set("border-radius", "var(--app-layout-menu-button-border-radius)");

        toggleWrapper = new Div();
        //toggleWrapper.getStyle().set("--expand-icon-fill-color", "var(--iron-icon-fill-color, none)");
        toggleWrapper.getClassNames().add("app-menu-item");
        toggleWrapper.getElement().setAttribute("slot", "submenu-trigger");
        toggleWrapper.getStyle().set("padding", "var(--app-layout-menu-button-padding)")
                .set("margin", "var(--app-layout-menu-button-margin)")
                .set("border-radius", "var(--app-layout-menu-button-border-radius)");

        item = new AppMenuIconItem(sectionName, icon.getElement().getAttribute("icon"));
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle().set("fill", "var(--expand-icon-fill-color)");
        toggleWrapper.add(item, ironIcon);
    }

    public void setParent(AppMenu parent) {
        this.parent = parent;
        menu.setParent(this);
    }
}
