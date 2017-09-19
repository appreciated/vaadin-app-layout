package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationButton;
import com.github.appreciated.app.layout.drawer.AbstractNavigationDrawer;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_ACTIVE;

public class DefaultNavigationElementComponentProvider implements ComponentProvider<NavigatorNavigationElement> {
    public static String UI_SESSION_KEY = "app-layout-menu-button-active";

    @Override
    public Component getComponent(NavigatorNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addClickListener(clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(element.getName());
            AbstractNavigationDrawer.closeDrawerIfNotPersistent();
            button.addStyleName(APP_LAYOUT_MENU_BUTTON_ACTIVE);
            Object oldMenuButton = UI.getCurrent().getSession().getAttribute(UI_SESSION_KEY);
            if (oldMenuButton != null && oldMenuButton instanceof NavigationButton) {
                ((NavigationButton) oldMenuButton).removeStyleName(APP_LAYOUT_MENU_BUTTON_ACTIVE);
            }
            UI.getCurrent().getSession().setAttribute(UI_SESSION_KEY, button);
        });
        return button;
    }
}
