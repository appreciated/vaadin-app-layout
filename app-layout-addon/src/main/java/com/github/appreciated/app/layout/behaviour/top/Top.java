package com.github.appreciated.app.layout.behaviour.top;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

@Tag("app-layout-top")
@HtmlImport("frontend://bower_components/vaadin-icons/vaadin-icons.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer-layout/app-drawer-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-header/app-header.html")
@HtmlImport("frontend://bower_components/app-layout/app-header-layout/app-header-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("frontend://bower_components/app-layout/app-scroll-effects/effects/waterfall.html")
@HtmlImport("frontend://com/github/appreciated/app-layout/top/top.html")
public class Top extends AbstractTopAppLayoutBase {

    @Override
    public String getStyleName() {
        return "top";
    }

}