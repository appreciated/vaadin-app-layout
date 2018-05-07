package com.github.appreciated.app.layout.component;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.*;

/**
 * A simple container component which may contain an image and two labels concerned component won't be added to its parent
 */
public class MenuHeader extends VerticalLayout {
    // FIXME You are using constructor chaining here. Thats a good practice ;)
    // Add missing JavaDoc
    public MenuHeader(String resource) {
        this(null, resource);
    }

    public MenuHeader(String title, String resource) {
        this(title, null, resource);
    }

    public MenuHeader(String title, String subtitle, String resource) {
        this(title, subtitle, resource != null ? new RoundImage(resource) : null);
    }

    public MenuHeader(String title, String subtitle, Image image) {

        getElement().getClassList().add(APP_LAYOUT_MENU_BAR_ELEMENT);
        getElement().getClassList().add(APP_LAYOUT_MENU_HEADER_ELEMENT);

        setSpacing(false);

        if (image != null) {
            add(image);
        }

        // FIXME These ifs should be reworked. They are not bulletproof because you ask for n object not to be null but adding another object without a null check
        // you should create an instance of Label only if the corresponding text is not null and add it afterwards.
        // BTW: not creating an instance if not required is better for memory usage and the GarbageCollector would be very happy about that ;)
        if (title != null) {
            Label name = new Label(title);
            name.getElement().getClassList().add(APP_LAYOUT_MENU_HEADER_TITLE);
            add(name);
        }
        if (subtitle != null) {
            Label description = new Label(subtitle);
            description.getElement().getClassList().add(APP_LAYOUT_MENU_HEADER_TITLE);
            add(description);
        }
    }

}