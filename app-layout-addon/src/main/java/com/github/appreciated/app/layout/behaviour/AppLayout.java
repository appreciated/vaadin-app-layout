package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.AppLayoutTheme;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.AbstractTheme;

/**
 * Every AppLayout is supposed to derive from a Polymer Template
 */
public abstract class AppLayout extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase, HasStyle {

    private Class<? extends AbstractTheme> theme;

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

    public Class<? extends AbstractTheme> getTheme() {
        return theme;
    }

    public void setTheme(Class<? extends AbstractTheme> theme) {
        this.theme = theme;
        this.getElement().getClassList().add(AppLayoutTheme.toTheme(theme).getThemeCssClass());
    }
}
