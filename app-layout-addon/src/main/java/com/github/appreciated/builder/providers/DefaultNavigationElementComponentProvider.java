package com.github.appreciated.builder.providers;

import com.github.appreciated.builder.ComponentProvider;
import com.github.appreciated.builder.elements.NavigatorNavigationElement;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class DefaultNavigationElementComponentProvider implements ComponentProvider<NavigatorNavigationElement> {
    @Override
    public Component getComponent(NavigatorNavigationElement element) {
        Button button = new Button(element.getName());
        button.setIcon(element.getIcon());
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addStyleName("app-layout-menu-button");
        button.addStyleName("no-border-radius"); // for material theme only
        button.setWidth(100, Sizeable.Unit.PERCENTAGE);
        button.addClickListener(clickEvent -> UI.getCurrent().getNavigator().navigateTo(element.getName()));
        return button;
    }
}
