package com.github.appreciated.applayout.behaviour;

import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.github.appreciated.applayout.webcomponents.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * The interface every AppLayout Variant is required to be implemented to allow any {@link com.github.appreciated.applayout.builder.AppLayoutBuilder} to build it.
 */

public interface AppLayoutElementBase {
    default void toggleDrawer() {
        getDrawer().getElement().callFunction("toggle");
    }

    default void openDrawer() {
        getDrawer().getElement().callFunction("open");
    }

    default void closeDrawerIfNotPersistent() {
        UI.getCurrent().getPage().executeJavaScript("if(!document.querySelector('app-drawer').hasAttribute('persistent')){document.querySelector('app-drawer').closeDrawer();}");
    }

    default void closeDrawer() {
        getDrawer().getElement().callFunction("toggle");
    }

    String getStyleName();

    void addToDrawerHeader(Component component);

    void addToMenu(Component component);

    void addToDrawerFooter(Component component);

    void addAppBarElement(Component component);

    void setDesign(AppLayoutDesign design);

    HorizontalLayout getAppBarElementWrapper();

    HasElement getTitleComponent();

    AppDrawer getDrawer();

    void setTitle(String title);

    void setTitleElement(HasElement titleComponent);

    HorizontalLayout getTitleWrapper();

    HasComponents getMenuElementHolder();

    void addAppBarIcon(Component appBarIconComponent);

    void setAppLayoutContent(HasElement content);

    void setBackNavigation(boolean visible);

    void setActiveElement(HasElement content);
}
