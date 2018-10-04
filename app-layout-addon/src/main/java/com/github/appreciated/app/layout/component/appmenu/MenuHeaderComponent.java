package com.github.appreciated.app.layout.component.appmenu;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * A simple container component which may contain an image and two labels concerned component won't be added to its parent
 */
public class MenuHeaderComponent extends Composite<VerticalLayout> {

    public MenuHeaderComponent(String title, String subtitle, String src) {

        VerticalLayout content = getContent();

        content.setMargin(false);
        setId("menu-header-wrapper");
        if (src != null) {
            content.add(new RoundImage(src, "56px", "56px"));
        }
        if (title != null) {
            Label titleLabel = new Label(title);
            titleLabel.setId("menu-header-title");
            content.add(titleLabel);
        }
        if (subtitle != null) {
            Label subtitleLabel = new Label(subtitle);
            subtitleLabel.setId("menu-header-subtitle");
            content.add(subtitleLabel);
        }
    }

}