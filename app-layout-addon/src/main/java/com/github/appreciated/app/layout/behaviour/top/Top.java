package com.github.appreciated.app.layout.behaviour.top;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.JavaScript;

import java.io.IOException;

/**
 * Created by Johannes on 01.05.2017.
 */
@HtmlImport("vaadin://bower_components/iron-icons/iron-icons.html")
@HtmlImport("vaadin://bower_components/paper-icon-button/paper-icon-button.html")
@HtmlImport("vaadin://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("vaadin://bower_components/app-layout/app-drawer/app-drawer.html")
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents-lite.js")

public class Top extends AbstractTopAppLayout {

    public Top() throws IOException {
        super("top.html");
    }

    @Override
    public String getStyleName() {
        return "top";
    }
}
