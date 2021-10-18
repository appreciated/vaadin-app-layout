package com.github.appreciated.app.layout.design;


import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;

@StyleSheet("./frontend/com/github/appreciated/app-layout/app-layout-styles-material.css")
@StyleSheet("./frontend/com/github/appreciated/app-layout/app-layout-styles-lumo.css")
/*
 * Vaadin seem to scan the classes on the production builds for {@link StyleSheet} annotations to pack the production
 * build. The problem is we only need one css at runtime, but both css files need to be in the production build
 */
public class ThemeHelper extends Div {
    private static final long serialVersionUID = 1L;

    public ThemeHelper() {
    }
}
