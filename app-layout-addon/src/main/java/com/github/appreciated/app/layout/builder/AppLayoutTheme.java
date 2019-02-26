package com.github.appreciated.app.layout.builder;

import com.vaadin.flow.theme.AbstractTheme;

import java.util.Arrays;

public enum AppLayoutTheme {
    LUMO("com.vaadin.flow.theme.lumo.Lumo", "frontend://com/github/appreciated/app-layout/app-layout-lumo.css"),
    MATERIAL("com.vaadin.flow.theme.material.Material", "frontend://com/github/appreciated/app-layout/app-layout-material.css");
    private String themeClassName;
    private String themeStylePath;

    AppLayoutTheme(String themeClassName, String themeStylePath) {
        this.themeClassName = themeClassName;
        this.themeStylePath = themeStylePath;
    }

    static public AppLayoutTheme toTheme(Class<? extends AbstractTheme> theme) {
        return Arrays.stream(values())
                .filter((appLayoutTheme) -> appLayoutTheme.getThemeClassName().equals(theme.getName()))
                .findFirst()
                .orElse(AppLayoutTheme.LUMO);
    }

    public String getThemeClassName() {
        return themeClassName;
    }

    public String getThemeStylePath() {
        return themeStylePath;
    }
}
