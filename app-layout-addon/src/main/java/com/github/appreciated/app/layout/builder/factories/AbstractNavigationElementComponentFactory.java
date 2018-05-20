package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeIconButton;

public abstract class AbstractNavigationElementComponentFactory implements ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> {

    public void setNavigationClickListener(NavigatorNavigationElement element) {
        NavigationBadgeIconButton button = (NavigationBadgeIconButton) element.getComponent();
        button.setClickListener(clickEvent -> {
            element.onClick();
            AppLayoutElementBase.closeDrawerIfNotPersistent();
        });
    }
}
