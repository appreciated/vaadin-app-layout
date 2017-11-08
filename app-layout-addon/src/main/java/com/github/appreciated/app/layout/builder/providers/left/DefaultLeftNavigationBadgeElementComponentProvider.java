package com.github.appreciated.app.layout.builder.providers.left;

import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.providers.AbstractNavigationElementComponentProvider;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;

public class DefaultLeftNavigationBadgeElementComponentProvider extends AbstractNavigationElementComponentProvider {

    @Override
    public NavigationElementComponent get(NavigatorNavigationElement element) {
        NavigationBadgeButton button = new NavigationBadgeButton(element.getCaption(), element.getIcon(), element.getBadgeHolder());
        element.setComponent(button);
        setNavigationClickListener(element);
        return button;
    }
}
