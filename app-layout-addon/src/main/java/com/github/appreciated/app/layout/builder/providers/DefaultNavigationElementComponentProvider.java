package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.builder.component.NavigationButton;
import com.github.appreciated.layout.drawer.AbstractNavigationDrawer;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class DefaultNavigationElementComponentProvider implements ComponentProvider<NavigatorNavigationElement> {
    @Override
    public Component getComponent(NavigatorNavigationElement element) {
        NavigationButton button = new NavigationButton(element.getName(), element.getIcon());
        button.addClickListener(clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(element.getName());
            AbstractNavigationDrawer.closeDrawerIfNotPersistent();
        });
        return button;
    }
}
