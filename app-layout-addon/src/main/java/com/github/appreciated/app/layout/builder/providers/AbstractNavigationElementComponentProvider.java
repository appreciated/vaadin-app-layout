package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.github.appreciated.app.layout.drawer.AbstractNavigationDrawer;
import com.github.appreciated.app.layout.session.NavigationElementHelper;
import com.vaadin.ui.UI;

public abstract class AbstractNavigationElementComponentProvider implements ComponentProvider<NavigatorNavigationElement> {
    public static String UI_SESSION_KEY = "app-layout-menu-button-active";

    public void setNavigationClickListener(NavigatorNavigationElement element) {
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        button.getButton().addClickListener(clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(element.getName());
            AbstractNavigationDrawer.closeDrawerIfNotPersistent();
            NavigationElementHelper.updateActiveElementSessionData(element);
        });
    }

}
