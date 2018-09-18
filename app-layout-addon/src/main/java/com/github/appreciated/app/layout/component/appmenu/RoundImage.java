package com.github.appreciated.app.layout.component.appmenu;

import com.vaadin.flow.component.html.Image;

/**
 * A component containing an image which is tweaked by css to be round without overflow with a certain height.
 */
public class RoundImage extends Image {
    public RoundImage(String icon) {
        this(icon, "75px", "75px");
    }

    public RoundImage(String icon, String width, String height) {
        super(icon, "Icon");
        setWidth(width);
        setHeight(height);
        getElement().getStyle().set("border-radius", "100%");
        setId("menu-header-image");
    }
}
