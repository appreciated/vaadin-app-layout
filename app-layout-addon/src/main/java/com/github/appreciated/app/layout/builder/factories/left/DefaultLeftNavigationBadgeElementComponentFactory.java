package com.github.appreciated.app.layout.builder.factories.left;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.factories.AbstractNavigationElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeIconButton;

public class DefaultLeftNavigationBadgeElementComponentFactory extends AbstractNavigationElementComponentFactory {

    @Override
    public NavigationElementComponent get(NavigatorNavigationElement element) {
        NavigationBadgeIconButton button = new NavigationBadgeIconButton(element);
        element.setComponent(button);
        setNavigationClickListener(element);
        return button;
    }
}
