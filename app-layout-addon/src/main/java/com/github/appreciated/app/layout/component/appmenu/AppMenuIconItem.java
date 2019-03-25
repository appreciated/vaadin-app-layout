package com.github.appreciated.app.layout.component.appmenu;

import com.github.appreciated.app.layout.component.appmenu.left.LeftMenuComponent;
import com.github.appreciated.ripple.PaperRipple;
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
        getElement().getClassList().add("app-menu-item");
        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
        add(new PaperRipple());
    }

    public AppMenuIconItem(String caption, Icon icon) {
        this();
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
        getElement().insertChild(0, this.icon.getElement());
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this.listener = listener;
    }

    public void setParent(LeftMenuComponent appMenu) {

    }
}