package com.github.appreciated.layout.drawer;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"vaadin://addons/app-layout/app-layout-connector.js"})
public class AppLayoutConnector extends AbstractJavaScriptComponent {

    public AppLayoutConnector() {

    }

    @Override
    protected AppLayoutConnectorState getState() {
        return (AppLayoutConnectorState) super.getState();
    }

}
