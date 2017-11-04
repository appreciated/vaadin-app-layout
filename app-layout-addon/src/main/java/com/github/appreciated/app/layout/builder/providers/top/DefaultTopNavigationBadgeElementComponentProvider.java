package com.github.appreciated.app.layout.builder.providers.top;

import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.providers.AbstractNavigationElementComponentProvider;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.vaadin.ui.themes.ValoTheme;

public class DefaultTopNavigationBadgeElementComponentProvider extends AbstractNavigationElementComponentProvider {

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
