package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * The interface every {@link Behaviour} is required
 * to be implemented to allow any {@link com.github.appreciated.app.layout.builder.AppLayoutBuilder} to build it.
 */

public interface AppLayoutElementBase extends NavigationElementContainer {

    String getStyleName();

    Component getTitleComponent();

    AppDrawer getDrawer();

    void setTitleComponent(Component titleComponent);

    HorizontalLayout getTitleWrapper();

    void setIconComponent(Component appBarIconComponent);

    void setAppLayoutContent(HasElement content);

    void setUpNavigation(boolean visible);

    void setAppBar(Component component);

    void setAppMenu(NavigationElementContainer component);

    default void init() {
    }
}
