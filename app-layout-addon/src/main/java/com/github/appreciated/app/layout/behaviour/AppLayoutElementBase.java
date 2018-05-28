package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.List;

/**
 * The interface every AppLayout Variant is required to be implemented to allow any {@link com.github.appreciated.app.layout.builder.AbstractAppLayoutBuilderBase} to build it.
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

    void addNavigationHeaderElement(AbstractNavigationElement component);

    void addNavigationFooterElement(AbstractNavigationElement component);

    void addNavigationElement(AbstractNavigationElement component);

    void addAppBarElement(Component component);

    void setDesign(AppLayoutDesign design);

    HorizontalLayout getAppBarElementWrapper();

    HasElement getTitleComponent();

    AppDrawer getDrawer();

    void setTitle(String title);

    void setTitleElement(HasElement titleComponent);

    HasElement getTitleWrapper();

    HasComponents getMenuElementHolder();

    HasComponents getMenuFooterHolder();

    HasComponents getMenuHeaderHolder();

    void addAppBarIcon(Component appBarIconComponent);

    void setNavigatorNavigationElements(List<NavigatorNavigationElement> list);

    void refreshNavigationElementInfo();

    ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getNavigationElementProvider();

    void setNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider);

    ComponentFactory<HasElement, SectionNavigationElement> getSectionElementProvider();

    void setSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider);

    ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> getSubmenuElementProvider();

    void setSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider);

    ComponentFactory<HasElement, ClickableNavigationElement> getClickableElementProvider();

    void setClickableElementProvider(ComponentFactory<HasElement, ClickableNavigationElement> provider);

    void setAppLayoutContent(HasElement content);
}
