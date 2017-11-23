package com.github.appreciated.app.layout.behaviour.listener;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import java.io.Serializable;

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