package com.github.appreciated.app.layout.builder.design;

/**
 * This enum contains all available designs. If {@link #DEFAULT} or {@link #MATERIAL} is not sufficient you can use {@link #CUSTOM} to
 * overwrite or provide your own styles.
 */
public enum AppLayoutDesign {
    /**
     * The default Design of the AppLayout
     */
    DEFAULT(Styles.APP_BAR_DESIGN_DEFAULT),

    /**
     * The material Design of the AppLayout which is meant to be used when a more material design is desired
     */
    MATERIAL(Styles.APP_BAR_DESIGN_MATERIAL),

    /**
     * A "Design" of the AppLayout which is meant to be used to have the full control of the css basically nothing is preset.
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
