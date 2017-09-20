package com.github.appreciated.app.layout.builder.design;

public enum AppBarDesign {
    DEFAULT("default-app-bar-design"), MATERIAL("material-app-bar-design"), CUSTOM("custom-app-bar-design");

    private String stylename;

    AppBarDesign(String s) {
        stylename = s;
    }

    public String getStylename() {
        return stylename;
    }
}
