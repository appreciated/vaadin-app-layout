package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

public class AppBarButton extends Button {
    public AppBarButton(Resource icon) {
        super(icon);
        setWidth("64px");
        setHeight("64px");
    }

    public AppBarButton(Resource icon, ClickListener listener) {
        this(icon);
        addClickListener(listener);
    }
}
