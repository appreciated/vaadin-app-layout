package com.github.appreciated.app.layout.behaviour.left;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

@Tag("app-layout-left-responsive-hybrid-no-app-bar")
@HtmlImport("frontend://bower_components/vaadin-icons/vaadin-icons.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer-layout/app-drawer-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-header/app-header.html")
@HtmlImport("frontend://bower_components/app-layout/app-header-layout/app-header-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-hybrid-no-app-bar.html")
public class LeftResponsiveHybridNoAppBar extends AbstractLeftAppLayoutBase {

    @Override
    public String getStyleName() {
        return "left-responsive-hybrid-no-app-bar";
    }
}
