package com.github.appreciated.app.layout.component.appbar;

import com.github.appreciated.app.layout.addons.notification.component.NotificationsView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;

public class ContextMenuAppBarButton extends ComponentBadgeWrapper<Button> {
    private final ContextMenu contextMenu;
    private Component buttonContent;

    public ContextMenuAppBarButton(Component icon) {
        super(new Button());
        getWrappedComponent().setIcon(icon);
        getWrappedComponent().addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
        setWidth("var(--app-layout-menu-button-height)");
        setHeight("var(--app-layout-menu-button-height)");
        contextMenu = new ContextMenu();
        contextMenu.setOpenOnClick(true);
        contextMenu.setTarget(this);
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

    protected void setContentComponent(NotificationsView notificationsView) {

    }
}
