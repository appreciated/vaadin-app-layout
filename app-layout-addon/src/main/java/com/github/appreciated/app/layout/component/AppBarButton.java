package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

import static com.github.appreciated.app.layout.Styles.APP_BAR_BUTTON;

public class AppBarButton extends Button {
    public AppBarButton(Resource icon) {
        super(icon);
        setWidth("64px");
        setHeight("64px");
        addStyleNames(APP_BAR_BUTTON);
    }
}
