package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("app-menu-icon-item")
@HtmlImport("bower_components/app-menu/app-menu-icon-item.html")
public class AppMenuIconItem extends Component implements HasSize, HasText {

    private ComponentEventListener<ClickEvent<AppMenuIconItem>> listener;

    public AppMenuIconItem() {
        setWidth("100%");
        setHeight("var(--app-layout-menu-button-height)");
        getElement().getStyle().set("line-height", "var(--app-layout-menu-button-height)");


        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public AppMenuIconItem(String caption) {
        this();
        setText(caption);
    }

    public AppMenuIconItem(String caption, String icon) {
        this(caption);
        setIcon(icon);
    }

    public AppMenuIconItem(String caption, String icon, ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this(caption, icon);
        this.listener = listener;
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        this.listener = listener;
    }
}

