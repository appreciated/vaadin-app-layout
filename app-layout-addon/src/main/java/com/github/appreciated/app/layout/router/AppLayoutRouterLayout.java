package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.AbstractAppLayoutBuilderBase;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@StyleSheet("frontend://com/github/appreciated/app-layout/app-layout.css")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout extends Div implements RouterLayout {

    private AppLayoutElementBase appLayoutElementBase;
    private HasElement currentContent;

    public AppLayoutRouterLayout() {
        setSizeFull();
        loadConfiguration();
        UI.getCurrent().getSession().setAttribute("app-layout", this);
    }

    public void reloadConfiguration() {
        removeAll();
        loadConfiguration();
        if (currentContent != null) {
            showRouterLayoutContent(currentContent);
        }
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        currentContent = content;
        appLayoutElementBase.setAppLayoutContent(content);
        if (content.getClass().getAnnotation(Route.class) != null) {
            int value = content.getClass().getAnnotation(Route.class).value().split("/").length;
            appLayoutElementBase.setBackNavigation(value > 1);
        }
    }

    private void loadConfiguration() {
        appLayoutElementBase = (AppLayoutElementBase) getAppLayoutElementBase().build();
        add((Component) appLayoutElementBase);
    }

    public static AppLayoutRouterLayout getCurrent() {
        return (AppLayoutRouterLayout) UI.getCurrent().getSession().getAttribute("app-layout");
    }

    public abstract AbstractAppLayoutBuilderBase getAppLayoutElementBase();

    public void closeDrawer() {
        appLayoutElementBase.closeDrawer();
    }

    public void toggleDrawer() {
        appLayoutElementBase.toggleDrawer();
    }

    public void openDrawer() {
        appLayoutElementBase.openDrawer();
    }

    public void closeDrawerIfNotPersistent() {
        appLayoutElementBase.closeDrawerIfNotPersistent();
    }

}
