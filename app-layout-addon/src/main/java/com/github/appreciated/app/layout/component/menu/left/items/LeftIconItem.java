package com.github.appreciated.app.layout.component.menu.left.items;

import com.github.appreciated.ripple.PaperRipple;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.RouterLink;

public class LeftIconItem extends RouterLink implements HasSize {
    private static final long serialVersionUID = 1L;

    private ComponentEventListener<ClickEvent<LeftIconItem>> listener;
    private Span caption;
    private Component icon;

    public LeftIconItem(String caption, Component icon) {
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
        caption = new Span(text);
        add(caption);
    }

    public void setIcon(Component icon) {
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
