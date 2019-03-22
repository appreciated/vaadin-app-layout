package com.github.appreciated.app.layout.component.appmenu;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.router.RouterLink;

public class AppMenuIconItem extends RouterLink implements HasSize {

    private ComponentEventListener<ClickEvent<AppMenuIconItem>> listener;
    private Label caption;
    private Icon icon;

    public AppMenuIconItem() {
        getElement().getClassList().set("app-menu-icon-item", true);
        setWidth("100%");
        setHeight("var(--app-layout-menu-button-height)");
        getElement().getStyle().set("line-height", "var(--app-layout-menu-button-height)");


        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public AppMenuIconItem(String caption, Icon icon, ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this(caption, icon);
        this.listener = listener;
    }

    public AppMenuIconItem(String caption, Icon icon) {
        setCaption(caption);
        setIcon(icon);
    }

    public void setCaption(String text) {
        if (caption != null) {
            remove(caption);
        }
        caption = new Label(text);
        add(caption);
    }

    public void setIcon(Icon icon) {
        if (this.icon != null) {
            remove(caption);
        }
        this.icon = icon;
        add(this.icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this.listener = listener;
    }

    public void setParent(AppMenu appMenu) {

    }
}