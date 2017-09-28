package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BAR_ELEMENT;

public class MenuHeader extends VerticalLayout {
    public MenuHeader(String title, String subtitle, Resource resource) {
        Label name = new Label(title);
        Label description = new Label(subtitle);
        description.addStyleName(ValoTheme.LABEL_SMALL);
        Image image = new Image(null, resource);
        image.setWidth("75px");
        image.setHeight("75px");
        image.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        this.addStyleName(APP_LAYOUT_MENU_BAR_ELEMENT);
        this.setMargin(false);
        this.setSpacing(false);
        this.setMargin(new MarginInfo(true, false));
        this.addComponents(image, name, description);
    }
}