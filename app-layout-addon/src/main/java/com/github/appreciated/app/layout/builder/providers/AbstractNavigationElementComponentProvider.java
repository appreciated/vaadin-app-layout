package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.behaviour.AbstractAppLayoutBehaviour;
import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.vaadin.ui.UI;

public abstract class AbstractNavigationElementComponentProvider implements ComponentProvider<NavigatorNavigationElement> {


    public void setNavigationClickListener(NavigatorNavigationElement element) {
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        button.getButton().addClickListener(clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(element.getViewName());
            AbstractAppLayoutBehaviour.closeDrawerIfNotPersistent();
        });
    }
}
