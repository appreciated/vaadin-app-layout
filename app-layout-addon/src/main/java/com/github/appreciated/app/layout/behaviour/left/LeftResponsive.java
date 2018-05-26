package com.github.appreciated.app.layout.behaviour.left;

import com.vaadin.flow.component.dependency.HtmlImport;


/**
 * Created by appreciated on 01.05.2017.
 */
//@JavaScript("vaadin://addons/app-layout/babel-helpers.js")
//@JavaScript("vaadin://addons/app-layout/app-layout-es5-listener.js")
//@JavaScript("frontend://bower_components/webcomponentsjs/webcomponents-lite.js")

@HtmlImport("frontend://com/github/appreciated/app/layout/behaviour/left/left-responsive.html")
public class LeftResponsive extends AbstractLeftAppLayoutBase {

    @Override
    public String getStyleName() {
        return "left-responsive";
    }
}