package com.github.appreciated.app.layout.component;

import com.vaadin.flow.component.html.Image;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_LAYOUT_ROUND_IMAGE;

/**
 * A component containing an image which is tweaked by css to be round without overflow with a certain height.
 */
public class RoundImage extends Image {
    public RoundImage(String icon) {
        this(icon, "75px", "75px");
    }

    public RoundImage(String icon, String width, String height) {
//        super((String) null, icon);
        super();
        setWidth(width);
        setHeight(height);
        getElement().getClassList().add(APP_LAYOUT_ROUND_IMAGE);
    }
}
