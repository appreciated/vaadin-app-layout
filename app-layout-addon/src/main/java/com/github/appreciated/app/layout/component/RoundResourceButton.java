package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

import static com.github.appreciated.app.layout.Styles.ROUND_RESOURCE_BUTTON;

public class RoundResourceButton extends Button {
    public RoundResourceButton(Resource icon) {
        super(icon);
        setWidth("75px");
        setHeight("75px");
        addStyleNames(ROUND_RESOURCE_BUTTON);
    }
}
