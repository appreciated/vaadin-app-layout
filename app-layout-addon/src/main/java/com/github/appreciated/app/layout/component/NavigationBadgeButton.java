package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;


public class NavigationBadgeButton extends AbsoluteLayout {

    private final NavigationButton button;
    private final Label badge;

    public NavigationBadgeButton(String name, Resource icon, BadgeCaptionProvider badgeContent) {
        setHeight(48, Unit.PIXELS);
        setWidth(100, Sizeable.Unit.PERCENTAGE);
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        badge = new Label();
        String value = null;
        if (badgeContent != null) {
            value = badgeContent.provideCaption(badge);
        }
        if (value != null) {
            badge.setVisible(true);
            badge.setValue(value);
        } else {
            badge.setVisible(false);
        }
        badge.addStyleName(APP_LAYOUT_MENU_BUTTON_BADGE);
        ComponentPosition badgePosition = new ComponentPosition();

        addComponent(button);
        addComponent(badge, "right: 0px;");
    }

    public NavigationButton getButton() {
        return button;
    }

    @FunctionalInterface
    public interface BadgeCaptionProvider {
        String provideCaption(Label label);
    }
}
