package com.github.appreciated.app.layout.builder.factories.top;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.factories.AbstractNavigationElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeIconButton;

public class DefaultTopNavigationBadgeElementComponentFactory extends AbstractNavigationElementComponentFactory {

    @Override
    public NavigationElementComponent get(NavigatorNavigationElement element) {
        NavigationBadgeIconButton button = new NavigationBadgeIconButton(element.getCaption(), element.getIcon(), element.getBadgeHolder());
        element.setComponent(button);
        button.setNavigationCaption(element.getCaption());
        setNavigationClickListener(element);
        return button;
    }
}
