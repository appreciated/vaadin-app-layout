package com.github.appreciated.app.layout.behaviour.listener;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import java.io.Serializable;

/**
 * This Java Script Component was added to notify the serverside that the width of the content view was changed this was
 * necessary due to issues with the Vaadin Grid
 */
@JavaScript({"vaadin://addons/app-layout/app-layout-resize-listener.js"})
public class AppLayoutResizeListener extends AbstractJavaScriptComponent {

    public AppLayoutResizeListener(AppLayoutResizedListener listener) {
        addFunction("onAppLayoutResized", (JavaScriptFunction) arguments -> listener.onAppLayoutResized());
    }

    @Override
    protected AppLayoutResizeListenerState getState() {
        return (AppLayoutResizeListenerState) super.getState();
    }

    public interface AppLayoutResizedListener extends Serializable {
        void onAppLayoutResized();
    }
}