package com.github.appreciated.app.layout.component.menu.left;

import com.github.appreciated.IronCollapseLayout;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.menu.left.items.LeftIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;

import java.util.List;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class LeftSubmenu extends Composite<IronCollapseLayout> implements NavigationElementContainer, AfterNavigationObserver, BeforeLeaveObserver {
    private final LeftSubmenuContainer submenuContainer;
    private final Div toggleWrapper;
    private final LeftIconItem item;
    private final IronIcon ironIcon;
    private final String caption;
    private final Icon icon;
    private NavigationElementContainer parent;
    private NavigationElement active;
    private boolean close = true;

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
        item.setHighlightAction((routerLink, highlight) -> {
        });
        ironIcon = new IronIcon("icons", "expand-more");

        ironIcon.getElement().getStyle()
                .set("fill", "var(--expand-icon-fill-color)")
                .set("position", "absolute")
                .set("right", "var(--app-layout-menu-toggle-button-padding)")
                .set("top", "50%")
                .set("transform", "translate(0%, -50%) rotate(0deg)")
                .set("transition", "transform 0.3s ease")
                .set("pointer-events", "none");

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
        this.parent = parent;
    }

    @Override
    public void setActiveNavigationElement(NavigationElement active) {
        if (active != null) {
            this.active = active;
        }
        if (this.parent != null) {
            parent.setActiveNavigationElement(active);
        }
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

    public void setCloseMenuOnNavigation(boolean close) {
        this.close = close;
    }

    public LeftSubmenu withCloseMenuOnNavigation(boolean close) {
        this.close = close;
        return this;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        if (active != null) {
            item.getElement().setAttribute("highlight", true);
        } else {
            item.getElement().removeAttribute("highlight");
            if (this.close) {
                getContent().getIronCollapse().hide();
            }
        }
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {
        this.active = null;
    }
}
