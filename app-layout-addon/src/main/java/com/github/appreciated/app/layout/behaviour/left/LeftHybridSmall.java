package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.polymertemplate.Id;

public class LeftHybridSmall extends LeftHybrid {

    @Id("drawerLayout")
    AppDrawerLayout layout;

    public LeftHybridSmall() {
        layout.getElement().getClassList().add("small");
    }

}
