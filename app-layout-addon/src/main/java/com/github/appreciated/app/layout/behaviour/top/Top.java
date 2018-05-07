package com.github.appreciated.app.layout.behaviour.top;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

@JavaScript("vaadin://addons/app-layout/babel-helpers.js")
@JavaScript("vaadin://addons/app-layout/app-layout-es5-listener.js")
@JavaScript("frontend://bower_components/webcomponentsjs/webcomponents-lite.js")

@HtmlImport("frontend://bower_components/polymer/polymer.html")
@HtmlImport("frontend://bower_components/iron-icons/iron-icons.html")
@HtmlImport("frontend://bower_components/paper-icon-button/paper-icon-button.html")
@HtmlImport("frontend://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer/app-drawer.html")


@Tag("top")
@HtmlImport("src/com/github/appreciated/app/layout/behaviour/top/top.html")
public class Top extends AbstractTopAppLayoutBase {

    private final HorizontalLayout appHeaderHolder = new HorizontalLayout();
    private final HorizontalLayout appElementHolder = new HorizontalLayout();
    private final HorizontalLayout appFooterHolder = new HorizontalLayout();

    private final HorizontalLayout appbarMenuHolder = new HorizontalLayout(appHeaderHolder, appElementHolder, appFooterHolder);

    public Top() {
        appHeaderHolder.setAlignItems(FlexComponent.Alignment.CENTER);
        appElementHolder.setAlignItems(FlexComponent.Alignment.CENTER);
        appFooterHolder.setAlignItems(FlexComponent.Alignment.CENTER);
        appbarMenuHolder.setFlexGrow(1.0, appElementHolder);
        getTitleWrapper().add(appbarMenuHolder);
        ((HorizontalLayout) getTitleWrapper()).setFlexGrow(1.0, appbarMenuHolder);
        appbarMenuHolder.getElement().getClassList().add("app-layout-bar-menu-holder");
        appbarMenuHolder.setHeight("100%");
    }

    @Override
    public String getStyleName() {
        return "top";
    }

    @Override
    public void addToTopHeader(Component component) {
        appHeaderHolder.add(component);
    }

    @Override
    public void addToTop(Component component) {
        appElementHolder.add(component);
    }

    @Override
    public void addToTopFooter(Component component) {
        appFooterHolder.add(component);
    }
}
