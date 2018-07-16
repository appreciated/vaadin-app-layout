package com.github.appreciated.applayout.behaviour.left;

import com.github.appreciated.applayout.webcomponents.applayout.AppDrawerLayout;
import com.vaadin.flow.component.polymertemplate.Id;

public class LeftHybridSmall extends LeftHybrid {

    @Id("drawerLayout")
    AppDrawerLayout layout;

    public LeftHybridSmall() {
        layout.getElement().getClassList().add("small");
    }

}
