package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

import static com.github.appreciated.app.layout.Styles.ROUND_RESOURCE_BUTTON;

public class RoundResourceButton extends Button {
    public RoundResourceButton(Resource icon) {
        this(icon, "75px", "75px");
    }

    public RoundResourceButton(Resource icon, String width, String height) {
        super(icon);
        setWidth(width);
        setHeight(height);
        addStyleNames(ROUND_RESOURCE_BUTTON);
    }

    public RoundResourceButton(Resource icon, ClickListener listener) {
        this(icon);
        addClickListener(listener);
    }

    public RoundResourceButton(Resource icon, String width, String height, ClickListener listener) {
        this(icon, width, height);
        addClickListener(listener);
    }
}
