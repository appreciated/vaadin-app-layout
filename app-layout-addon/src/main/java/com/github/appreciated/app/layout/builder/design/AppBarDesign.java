package com.github.appreciated.app.layout.builder.design;

import com.github.appreciated.app.layout.Styles;

/**
 * This enum contains all available designs. If {@link #DEFAULT} or {@link #MATERIAL} is not sufficient you can use {@link #CUSTOM} to
 * overwrite or provide your own styles.
 */
public enum AppBarDesign {
    /**
     * FIXME add JavaDoc
     */
    DEFAULT(Styles.APP_BAR_DESIGN_DEFAULT),

    /**
     * FIXME add JavaDoc
     */
    MATERIAL(Styles.APP_BAR_DESIGN_MATERIAL),

    /**
     * FIXME add JavaDoc even with a codesample how a custom style can be used
     */
    CUSTOM(Styles.APP_BAR_DESIGN_CUSTOM);

    private String styleName;

    private AppBarDesign(String styleName) {
        this.styleName = styleName;
    }

    public String getStylename() {
        return styleName;
    }
}
