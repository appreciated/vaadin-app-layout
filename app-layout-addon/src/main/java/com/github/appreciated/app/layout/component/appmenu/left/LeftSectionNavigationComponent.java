package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.Factory;

import java.awt.*;


/**
 * A wrapper class for a menu element that is a {@link LeftSectionNavigationComponent}.
 */
public class LeftSectionNavigationComponent extends Label {

    private String name;
    private Factory<String, String> captionInterceptor;

    public LeftSectionNavigationComponent(String name) {
        this.name = name;
        setText(name);
    }

    public String getName() {
        if (captionInterceptor == null) {
            return name;
        } else {
            return captionInterceptor.get(name);
        }
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
