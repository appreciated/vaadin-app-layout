package com.github.appreciated.app.layout.component.appmenu.left;

import com.vaadin.flow.component.html.Label;

import java.util.function.Function;


/**
 * A wrapper class for a menu element that is a {@link LeftSectionNavigationComponent}.
 */
public class LeftSectionNavigationComponent extends Label {

    private String name;
    private Function<String, String> captionInterceptor;

    public LeftSectionNavigationComponent(String name) {
        this.name = name;
        setText(name);
    }

    public String getName() {
        if (captionInterceptor == null) {
            return name;
        } else {
            return captionInterceptor.apply(name);
        }
    }

    public void setCaptionInterceptor(Function<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }
}
