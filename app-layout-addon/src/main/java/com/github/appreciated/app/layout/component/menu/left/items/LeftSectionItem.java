package com.github.appreciated.app.layout.component.menu.left.items;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;

import java.util.function.Function;


/**
 * A wrapper class for a menu element that is a {@link LeftSectionItem}.
 */
public class LeftSectionItem extends Div {

    private String name;
    private Function<String, String> captionInterceptor;

    public LeftSectionItem(String name) {
        this();
        this.name = name;
        add(new H3(name));
    }

    public LeftSectionItem() {
        getStyle().set("border-top", "1px grey");
    }

    public String getName() {
        if (captionInterceptor == null) {
            return name;
        } else {
            return captionInterceptor.apply(name);
        }
    }
}
