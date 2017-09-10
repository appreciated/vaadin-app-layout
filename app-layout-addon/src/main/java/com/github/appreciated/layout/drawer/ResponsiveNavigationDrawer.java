package com.github.appreciated.layout.drawer;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.JavaScript;

import java.io.IOException;

/**
 * Created by Johannes on 01.05.2017.
 */
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents-lite.js")

@HtmlImport("vaadin://bower_components/font-roboto/roboto.html")
@HtmlImport("vaadin://bower_components/iron-icons/iron-icons.html")
@HtmlImport("vaadin://bower_components/paper-icon-button/paper-icon-button.html")
@HtmlImport("vaadin://bower_components/paper-item/paper-icon-item.html")
@HtmlImport("vaadin://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("vaadin://bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("vaadin://bower_components/app-layout/app-drawer-layout/app-drawer-layout.html")

public class ResponsiveNavigationDrawer extends AbstractNavigationDrawer {

    public ResponsiveNavigationDrawer() throws IOException {
        super("responsive-navigation-drawer.html");
    }

    @Override
    public String getStyleName() {
        return "app-drawer-responsive";
    }
}