package com.github.appreciated.app.layout.component.menu.left.items;

import com.github.appreciated.app.layout.component.menu.left.LeftMenu;
import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.router.RouterLink;

public class LeftIconItem extends RouterLink implements HasSize {

    private ComponentEventListener<ClickEvent<LeftIconItem>> listener;
    private Label caption;
    private Icon icon;

    public LeftIconItem(String caption, Icon icon) {
        this();
        setCaption(caption);
        setIcon(icon);
    }

    public LeftIconItem() {
        getElement().getClassList().add("app-menu-item");
        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
        add(new PaperRipple());
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
        if (icon != null) {
            getElement().insertChild(0, this.icon.getElement());
        }
    }

    public void setClickListener(ComponentEventListener<ClickEvent<LeftIconItem>> listener) {
        this.listener = listener;
    }
}