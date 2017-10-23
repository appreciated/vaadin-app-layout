package com.github.appreciated.app.layout.behaviour.impl;

import com.github.appreciated.app.layout.behaviour.AbstractLeftAppLayout;
import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.JavaScript;

import java.io.IOException;

/**
 * Created by Johannes on 01.05.2017.
 */
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents-lite.js")

@HtmlImport("vaadin://bower_components/iron-icons/iron-icons.html")
@HtmlImport("vaadin://bower_components/paper-icon-button/paper-icon-button.html")
@HtmlImport("vaadin://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("vaadin://bower_components/app-layout/app-drawer/app-drawer.html")

public class LeftOverlay extends AbstractLeftAppLayout {

    public LeftOverlay() throws IOException {
        super("left-overlay.html");
    }

    @Override
    public String getStyleName() {
        return "left-overlay";
    }
}
