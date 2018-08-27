package com.github.appreciated.applayout.component.appmenu;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * A simple container component which may contain an image and two labels concerned component won't be added to its parent
 */
public class MenuHeaderComponent extends VerticalLayout {

    public MenuHeaderComponent(String title, String subtitle, String src) {
        setMargin(false);
        setId("menu-header-wrapper");
        if (src != null) {
            add(new RoundImage(src, "56px", "56px"));
        }
        if (title != null) {
            Label titleLabel = new Label(title);
            titleLabel.setId("menu-header-title");
            add(titleLabel);
        }
        if (subtitle != null) {
            Label subtitleLabel = new Label(subtitle);
            subtitleLabel.setId("menu-header-subtitle");
            add(subtitleLabel);
        }
    }

}