package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BAR_ELEMENT;

public class MenuHeader extends VerticalLayout {
    public MenuHeader(String title, String subtitle, Resource resource) {
        Label name = new Label(title);
        name.addStyleName(ValoTheme.LABEL_H4);
        Label description = new Label(subtitle);
        description.addStyleName(ValoTheme.LABEL_SMALL);
        RoundResourceButton resourceButton = new RoundResourceButton(resource, "75px", "75px");
        addStyleName(APP_LAYOUT_MENU_BAR_ELEMENT);
        setMargin(false);
        setSpacing(false);
        setMargin(new MarginInfo(true, false));
        addComponents(resourceButton, name, description);
    }
}