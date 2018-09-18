package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("app-menu-icon-item")
@HtmlImport("bower_components/app-menu/app-menu-icon-item.html")
public class AppMenuIconItem extends Component implements HasSize {

    private ComponentEventListener<ClickEvent<AppMenuIconItem>> listener;

    public AppMenuIconItem() {
        setWidth("100%");
        setHeight("48px");
        getElement().getStyle().set("line-height", "48px");
        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public AppMenuIconItem(String sectionName) {
        this();
        setText(sectionName);
    }

    public AppMenuIconItem(String sectionName, String icon) {
        this(sectionName);
        setIcon(icon);
    }

    public AppMenuIconItem(String sectionName, String icon, ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this(sectionName, icon);
        this.listener = listener;
    }

    public void setText(String sectionName) {
        getElement().setText(sectionName);
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this.listener = listener;
    }
}

