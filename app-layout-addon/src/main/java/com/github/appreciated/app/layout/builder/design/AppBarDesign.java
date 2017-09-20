package com.github.appreciated.app.layout.builder.design;

import com.github.appreciated.app.layout.Styles;

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
