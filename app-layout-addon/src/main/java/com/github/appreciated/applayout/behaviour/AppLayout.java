package com.github.appreciated.applayout.behaviour;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

public abstract class AppLayout extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase, HasStyle {

    public void toggleDrawer() {
        getDrawer().getElement().callFunction("toggle");
    }

    public void openDrawer() {
        getDrawer().getElement().callFunction("open");
    }

    public void closeDrawerIfNotPersistent() {
        getElement().callFunction("closeIfNotPersistent");
    }

    public void closeDrawer() {
        getDrawer().getElement().callFunction("close");
    }

}
