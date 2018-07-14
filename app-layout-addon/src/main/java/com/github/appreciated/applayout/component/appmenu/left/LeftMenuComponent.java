package com.github.appreciated.applayout.component.appmenu.left;

import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.webcomponents.appmenu.AppMenu;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

public class LeftMenuComponent extends AppMenu implements NavigationElementContainer {
    @Override
    public boolean setActiveNavigationElementWithViewClass(HasElement element) {
        return getChildren()
                .filter(component -> component instanceof NavigationElementContainer)
                .map(component -> ((NavigationElementContainer) component).setActiveNavigationElementWithViewClass(element))
                .reduce((first, next) -> first || next).orElse(false);
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
