package com.github.appreciated.app.layout.behaviour.top;

import com.github.appreciated.app.layout.component.HorizontalFlexBoxLayout;
import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

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

    private final CssLayout appHeaderHolder = new CssLayout();
    private final CssLayout appElementHolder = new CssLayout();
    private final CssLayout appFooterHolder = new CssLayout();

    private final HorizontalFlexBoxLayout appbarMenuHolder = new HorizontalFlexBoxLayout(appHeaderHolder, appElementHolder, appFooterHolder);

    public Top() throws IOException {
        super("top.html");
        getAppBar().addComponent(appbarMenuHolder, 1);
        appbarMenuHolder.setHeight(100, Unit.PERCENTAGE);
    }


    @Override
    public String getStyleName() {
        return "top";
    }

    @Override
    public void addToTopHeader(Component component) {
        appHeaderHolder.addComponent(component);
    }

    @Override
    public void addToTop(Component component) {
        appElementHolder.addComponent(component);
    }

    @Override
    public void addToTopFooter(Component component) {
        appFooterHolder.addComponent(component);
    }
}
