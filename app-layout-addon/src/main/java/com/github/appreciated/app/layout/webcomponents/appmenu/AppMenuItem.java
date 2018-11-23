package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.github.appreciated.app.layout.webcomponents.paperripple.PaperRipple;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Anchor;

public class AppMenuItem extends Anchor {

    private AppMenuIconItem item;
    private AppMenu parent;

    public AppMenuItem() {
        getElement().getClassList().add("app-menu-item");
        getElement().setAttribute("href", "javascript:void(0)");
        getElement().getStyle().set("position", "relative")
                .set("padding", "var(--app-layout-menu-button-padding)")
                .set("margin", "var(--app-layout-menu-button-margin)")
                .set("border-radius", "var(--app-layout-menu-button-border-radius)");
        getElement().appendChild(new PaperRipple().getElement());
    }

    private AppMenuItem(String sectionName, String icon, boolean hasIcon) {
        this();
        if (hasIcon) {
            item = new AppMenuIconItem();
            item.setIcon(icon);
            item.setText(sectionName);
            add(item);
        }
        setText(sectionName);
    }

    public AppMenuItem(String sectionName) {
        this(sectionName, null, false);
    }

    public AppMenuItem(String sectionName, String icon) {
        this(sectionName, icon, true);
        setIcon(icon);
    }

    public AppMenuItem(String sectionName, String icon,
                       ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this(sectionName, icon);
        item.setClickListener(listener);
    }

    @Override
    public String getText() {
        if (item != null) {
            return item.getText();
        } else {
            return getElement().getText();
        }
    }

    @Override
    public void setText(String sectionName) {
        if (item != null) {
            item.setText(sectionName);
        } else {
            getElement().setText(sectionName);
        }
    }

    public void setIcon(String icon) {
        if (item == null) {
            item = new AppMenuIconItem();
            item.setText(getElement().getText());
            getElement().setText(null);
            add(item);
        }
        item.setIcon(icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        if (item != null) {
            item.setClickListener(listener);
        } else {
            getElement().addEventListener("click", domEvent -> listener.onComponentEvent(null));
        }
    }

    public AppMenuIconItem getItem() {
        return item;
    }

    public void setParent(AppMenu parent) {
        this.parent = parent;
    }

    public void setActive() {
        parent.setSelected(this);
    }
}
