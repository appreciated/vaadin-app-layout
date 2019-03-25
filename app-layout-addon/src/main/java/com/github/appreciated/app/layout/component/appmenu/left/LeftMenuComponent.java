package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appmenu.SubmenuContainer;

public class LeftMenuComponent extends SubmenuContainer implements NavigationElementContainer {

    public LeftMenuComponent() {
        getElement().getThemeList().set("spacing",false);
        getElement().getThemeList().set("spacing-s",true);
    }
}
