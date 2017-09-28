package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Image;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_ROUND_IMAGE;

public class RoundImage extends Image {
    public RoundImage(Resource icon) {
        this(icon, "75px", "75px");
    }

    public RoundImage(Resource icon, String width, String height) {
        super(null, icon);
        setWidth(width);
        setHeight(height);
        addStyleNames(APP_LAYOUT_ROUND_IMAGE);
    }
}
