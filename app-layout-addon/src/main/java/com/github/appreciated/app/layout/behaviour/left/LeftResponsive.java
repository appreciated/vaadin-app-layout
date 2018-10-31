package com.github.appreciated.app.layout.behaviour.left;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


@Tag("app-layout-left-responsive")
@HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive.html")
public class LeftResponsive extends AbstractLeftAppLayoutBase {

    @Override
    public String getStyleName() {
        return "left-responsive";
    }
}
