package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.vaadin.ui.Component;

public class DefaultNavigationBadgeElementComponentProvider extends AbstractNavigationElementComponentProvider {

    @Override
    public Component getComponent(NavigatorNavigationElement element) {
        NavigationBadgeButton button = new NavigationBadgeButton(element.getName(), element.getIcon(), element.getBadgeHolder());
        element.setComponent(button);
        setNavigationClickListener(element);
        return button;
    }
}
