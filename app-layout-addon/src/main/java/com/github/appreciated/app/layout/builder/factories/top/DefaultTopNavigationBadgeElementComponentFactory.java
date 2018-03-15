package com.github.appreciated.app.layout.builder.factories.top;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.factories.AbstractNavigationElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeButton;
import com.vaadin.ui.themes.ValoTheme;

public class DefaultTopNavigationBadgeElementComponentFactory extends AbstractNavigationElementComponentFactory {

    @Override
    public NavigationElementComponent get(NavigatorNavigationElement element) {
        NavigationBadgeButton button = new NavigationBadgeButton(element.getCaption(), element.getIcon(), element.getBadgeHolder());
        element.setComponent(button);
        button.getButton().addStyleName(ValoTheme.BUTTON_SMALL);
        button.setDescription(element.getCaption());
        setNavigationClickListener(element);
        button.setWidthUndefined();
        return button;
    }
}
