package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;

public class ContextMenuAppBarButton extends ComponentBadgeWrapper<IconButton> {
    private static final long serialVersionUID = 1L;

    private final ContextMenu contextMenu;
    private Component buttonContent;

    public ContextMenuAppBarButton(Component icon) {
        super(new IconButton(icon));
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        contextMenu = new ContextMenu();
        contextMenu.setOpenOnClick(true);
        contextMenu.setTarget(this);
        ((Component) getBadge()).setVisible(false);
    }

    public Component getButtonContent() {
        return buttonContent;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void addItem(String text) {
        contextMenu.addItem(text);
    }

    public void addItem(Component component) {
        contextMenu.addItem(component);
    }

    public void addItem(String text, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(text, clickListener);
    }

    public void addItem(Component component, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(component, clickListener);
    }
}
