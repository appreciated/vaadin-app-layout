package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeButton;

import java.io.Serializable;

public abstract class AbstractNavigationElementComponentProvider implements Serializable, ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> {

    public void setNavigationClickListener(NavigatorNavigationElement element) {
        NavigationBadgeButton button = (NavigationBadgeButton) element.getComponent();
        button.getButton().addClickListener(clickEvent -> {
            element.onClick();
            AppLayoutComponent.closeDrawerIfNotPersistent();
        });
    }
}
