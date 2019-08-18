package com.github.appreciated.app.layout.component.appbar;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Label;

public class ContextMenuAppBarButton extends Button {
    private final ContextMenu contextMenu;
    private Component buttonContent;

    public ContextMenuAppBarButton(Component content) {
        this.buttonContent = content;
        setIcon(content);
        setThemeName("tertiary");
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        contextMenu = new ContextMenu();
        contextMenu.setOpenOnClick(true);
        contextMenu.setTarget(this);
        Label message = new Label("-");
        contextMenu.addItem("First menu item", e -> message.setText("Clicked on the first item"));
        contextMenu.addItem("Second menu item", e -> message.setText("Clicked on the second item"));
        MenuItem item = contextMenu.addItem("Disabled menu item", e -> message.setText("This cannot happen"));
        item.setEnabled(false);
    }

    public Component getButtonContent() {
        return buttonContent;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public ContextMenuAppBarButton withItem(String text) {
        contextMenu.addItem(text);
        return this;
    }

    public ContextMenuAppBarButton withItem(Component component) {
        contextMenu.addItem(component);
        return this;
    }

    public ContextMenuAppBarButton withItem(String text, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(text, clickListener);
        return this;
    }

    public ContextMenuAppBarButton withItem(Component component, ComponentEventListener<ClickEvent<MenuItem>> clickListener) {
        contextMenu.addItem(component, clickListener);
        return this;
    }

}
