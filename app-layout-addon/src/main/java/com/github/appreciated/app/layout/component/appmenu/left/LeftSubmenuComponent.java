package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.IronCollapseLayout;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appmenu.AppMenuIconItem;
import com.github.appreciated.app.layout.component.appmenu.SubmenuContainer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;

import java.util.List;
import java.util.stream.Stream;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class LeftSubmenuComponent extends Composite<IronCollapseLayout> implements NavigationElementContainer {
    private final SubmenuContainer submenuContainer;
    private final Div toggleWrapper;
    private final AppMenuIconItem item;
    private final IronIcon ironIcon;
    private final String caption;
    private final Icon icon;

    public LeftSubmenuComponent(String caption, Icon icon, List<Component> submenuElements) {
        submenuContainer = new SubmenuContainer();
        submenuContainer.getStyle()
                .set("border-radius", "var(--app-layout-menu-button-border-radius)")
                .set("background","var(--app-layout-drawer-submenu-background-color)");;
        getSubmenuContainer().add(submenuElements.toArray(new Component[]{}));

        toggleWrapper = new Div();
        toggleWrapper.getStyle().set("position","relative");

        item = new AppMenuIconItem(caption, icon);
        item.setHighlightCondition((routerLink, event) -> false);
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle()
                .set("fill", "var(--expand-icon-fill-color)")
                .set("position", "absolute")
                .set("right", "var(--app-layout-menu-toggle-button-padding)")
                .set("top", "50%")
                .set("transform", "translate(0%,-50%)");

        toggleWrapper.add(item, ironIcon);
        getContent().getElement().appendChild(toggleWrapper.getElement());
        getContent().addCollpasableContent(submenuContainer);
        getContent().getElement().getStyle().set("width", "100%");
        this.caption = caption;
        this.icon = icon;
    }

    public SubmenuContainer getSubmenuContainer() {
        return submenuContainer;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public Stream<Component> getMenuChildren() {
        return getSubmenuContainer().getChildren();
    }

    @Override
    public Component getComponent() {
        return submenuContainer;
    }
}
