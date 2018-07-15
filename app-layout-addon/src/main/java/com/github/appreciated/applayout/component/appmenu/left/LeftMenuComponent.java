package com.github.appreciated.applayout.component.appmenu.left;

import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.webcomponents.appmenu.AppMenu;
import com.vaadin.flow.component.Component;

public class LeftMenuComponent extends AppMenu implements NavigationElementContainer {

    @Override
    public Component getComponent() {
        return this;
    }
}
