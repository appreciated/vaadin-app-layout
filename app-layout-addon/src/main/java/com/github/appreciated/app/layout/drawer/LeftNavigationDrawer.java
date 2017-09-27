package com.github.appreciated.app.layout.drawer;

import com.vaadin.annotations.HtmlImport;

import java.io.IOException;

/**
 * Created by Johannes on 01.05.2017.
 */
@HtmlImport("vaadin://bower_components/iron-icons/iron-icons.html")
@HtmlImport("vaadin://bower_components/paper-icon-button/paper-icon-button.html")
@HtmlImport("vaadin://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("vaadin://bower_components/app-layout/app-drawer/app-drawer.html")

public class LeftNavigationDrawer extends AbstractNavigationDrawer {

    public LeftNavigationDrawer() throws IOException {
        super("left-navigation-drawer.html");
    }

    @Override
    public String getStyleName() {
        return "left-navigation-drawer";
    }
}
