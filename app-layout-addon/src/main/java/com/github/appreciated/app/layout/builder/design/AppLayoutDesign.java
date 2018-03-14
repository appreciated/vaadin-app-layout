package com.github.appreciated.app.layout.builder.design;

/**
 * This enum contains all available designs. If {@link #DEFAULT} or {@link #MATERIAL} is not sufficient you can use {@link #CUSTOM} to
 * overwrite or provide your own styles.
 */
public enum AppLayoutDesign {
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

    AppLayoutDesign(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }
}
