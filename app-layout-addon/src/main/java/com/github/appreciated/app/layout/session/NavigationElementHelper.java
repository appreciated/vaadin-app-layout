package com.github.appreciated.app.layout.session;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.vaadin.ui.UI;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT_ACTIVE;
import static com.github.appreciated.app.layout.builder.providers.AbstractNavigationElementComponentProvider.UI_SESSION_KEY;

public class NavigationElementHelper {
    public static void updateActiveElementSessionData(NavigatorNavigationElement element) {
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        button.addStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE);
        Object oldMenuButton = UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY);
        if (oldMenuButton != null && oldMenuButton instanceof NavigationBadgeButton) {
            ((NavigationBadgeButton) oldMenuButton).removeStyleName(APP_LAYOUT_MENU_ELEMENT_ACTIVE);
        }
        UI.getCurrent().getSession().setAttribute(UI_SESSION_KEY, button);
    }

}
