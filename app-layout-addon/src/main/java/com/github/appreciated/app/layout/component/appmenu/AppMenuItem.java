package com.github.appreciated.app.layout.component.appmenu;

import com.github.appreciated.app.layout.component.appmenu.left.LeftMenuComponent;
import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AppMenuItem extends HorizontalLayout {

    private AppMenuIconItem item;
    private LeftMenuComponent parent;

    public AppMenuItem(String sectionName) {
        this(sectionName, null, false);
    }

    private AppMenuItem(String sectionName, Icon icon, boolean hasIcon) {
        this();
        if (hasIcon) {
            item = new AppMenuIconItem();
            item.setIcon(icon);
            item.setCaption(sectionName);
            add(item);
        }
        setText(sectionName);
    }

    public AppMenuItem() {
        getElement().getClassList().add("app-menu-item");
        getElement().setAttribute("href", "javascript:void(0)");
        getElement().getStyle().set("position", "relative")
                .set("padding", "var(--app-layout-menu-button-padding)")
                .set("border-radius", "var(--app-layout-menu-button-border-radius)")
                .set("--lumo-primary-text-color", "var(--app-layout-app-color)")
                .set("text-decoration", "none");
        getElement().appendChild(new PaperRipple().getElement());
    }

    public AppMenuItem(String sectionName, Icon icon,
                       ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this(sectionName, icon);
        item.setClickListener(listener);
    }

    public AppMenuItem(String sectionName, Icon icon) {
        this(sectionName, icon, true);
    }

    public String getText() {
        return item != null
                ? item.getText()
                : getElement().getText();
    }

    public void setText(String sectionName) {
        if (item != null) item.setText(sectionName);
        else getElement().setText(sectionName);
    }

    public void setIcon(Icon icon) {
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

    public void setParent(LeftMenuComponent parent) {
        this.parent = parent;
    }

    public void setActive() {
        //parent.setSelected(this);
    }
}
