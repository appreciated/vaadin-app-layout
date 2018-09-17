package com.github.appreciated.applayout.behaviour;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * This interface contains the methods that any behaviour should offer to allow interchangeability.
 */

public abstract class AppLayout extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase, HasStyle {
    /**
     * Causes the the {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} to be toggled. Since this is a "low-level" function of the drawer this should be used with caution.
     * Malfunction might happen.
     */
    public void toggleDrawer() {
        getDrawer().getElement().callFunction("toggle");
    }

    /**
     * Causes the the {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} to be opened. Since this is a "low-level" function of the drawer this should be used with caution.
     * Malfunction might happen.
     */
    public void openDrawer() {
        getDrawer().getElement().callFunction("open");
    }

    /**
     * Causes the the {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} to be closed if not persistent.
     * This is a custom function of this addon and is supported.
     */
    public void closeDrawerIfNotPersistent() {
        getElement().callFunction("closeIfNotPersistent");
    }

    /**
     * Causes the the {@link com.github.appreciated.applayout.webcomponents.applayout.AppDrawer} to be closed. Since this is a "low-level" function of the drawer this should be used with caution.
     * Malfunction might happen.
     */
    public void closeDrawer() {
        getDrawer().getElement().callFunction("close");
    }

}
