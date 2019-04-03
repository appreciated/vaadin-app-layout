package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.IronCollapseLayout;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.menu.left.items.LeftIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;

import java.util.List;
import java.util.Optional;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class LeftSubmenu extends Composite<IronCollapseLayout> implements NavigationElementContainer {
    private final LeftSubmenuContainer submenuContainer;
    private final Div toggleWrapper;
    private final LeftIconItem item;
    private final IronIcon ironIcon;
    private final String caption;
    private final Icon icon;
    private Optional<NavigationElementContainer> parent = Optional.empty();

    public LeftSubmenu(String caption, Icon icon, List<Component> submenuElements) {
        submenuContainer = new LeftSubmenuContainer();
        submenuContainer.getStyle()
                .set("border-radius", "var(--app-layout-menu-button-border-radius)")
                .set("background", "var(--app-layout-drawer-submenu-background-color)");
        getSubmenuContainer().add(submenuElements.toArray(new Component[]{}));
        applyParentToItems(submenuElements.stream());
        toggleWrapper = new Div();
        toggleWrapper.getStyle().set("position", "relative");

        item = new LeftIconItem(caption, icon);
        item.setHighlightCondition((routerLink, event) -> false);
        item.setHighlightAction((routerLink, highlight) -> {});
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle()
                .set("fill", "var(--expand-icon-fill-color)")
                .set("position", "absolute")
                .set("right", "var(--app-layout-menu-toggle-button-padding)")
                .set("top", "50%")
                .set("transform", "translate(0%,-50%)")
                .set("pointer-events","none");

        toggleWrapper.add(item, ironIcon);
        getContent().getElement().appendChild(toggleWrapper.getElement());
        getContent().addCollapsibleContent(submenuContainer);
        getContent().getElement().getStyle().set("width", "100%");
        this.caption = caption;
        this.icon = icon;
    }

    public LeftSubmenuContainer getSubmenuContainer() {
        return submenuContainer;
    }

    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setNavigationElementContainer(NavigationElementContainer parent) {
        this.parent = Optional.of(parent);
    }

    @Override
    public void setActiveNavigationElement(boolean active) {
        if (active) {
            item.getElement().setAttribute("highlight", active);
        } else {
            item.getElement().removeAttribute("highlight");
        }
        this.parent.ifPresent(container -> container.setActiveNavigationElement(active));
    }

    public String getCaption() {
        return caption;
    }

    public LeftIconItem getItem() {
        return item;
    }

    public IronIcon getIronIcon() {
        return ironIcon;
    }
}
