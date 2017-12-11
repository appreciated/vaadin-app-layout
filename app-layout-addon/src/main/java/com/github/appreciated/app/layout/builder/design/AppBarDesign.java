package com.github.appreciated.app.layout.builder.design;

import com.github.appreciated.app.layout.Styles;

/**
 * This enum contains the designs available Default and Material. Custom was added to take the need for users to
 * overwrite many styling but instead provide their own
 */
public enum AppBarDesign {
    DEFAULT(Styles.APP_BAR_DESIGN_DEFAULT), MATERIAL(Styles.APP_BAR_DESIGN_MATERIAL), CUSTOM(Styles.APP_BAR_DESIGN_CUSTOM);

    private String stylename;

    AppBarDesign(String s) {
        stylename = s;
    }

    public String getStylename() {
        return stylename;
    }
}
