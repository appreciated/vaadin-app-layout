package com.github.appreciated.app.layout.builder;

import com.vaadin.flow.theme.AbstractTheme;

import java.util.Arrays;

public enum AppLayoutTheme {
    LUMO("com.vaadin.flow.theme.lumo.Lumo", "lumo"),
    MATERIAL("com.vaadin.flow.theme.material.Material", "material");
    private String themeClassName;
    private String themeCssClass;

    AppLayoutTheme(String themeClassName, String themeCssClass) {
        this.themeClassName = themeClassName;
        this.themeCssClass = themeCssClass;
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

    public String getThemeCssClass() {
        return themeCssClass;
    }
}
