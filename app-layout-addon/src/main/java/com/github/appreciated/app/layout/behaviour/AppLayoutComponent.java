package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.interfaces.ComponentProvider;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.List;

/**
 * The interface every AppLayout Variant is required to be implemented to allow the AppLayoutBuilder to build it.
 */

public interface AppLayoutComponent extends Component {
    static void toggleDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').toggle();");
    }

    static void openDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').open();");
    }

    static void closeDrawerIfNotPersistent() {
        Page.getCurrent().getJavaScript().execute("if(!document.querySelector('app-drawer').hasAttribute('persistent')){document.querySelector('app-drawer').close();}");
    }

    static void closeDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').close();");
    }

    String getStyleName();

    void addToDrawerHeader(Component component);

    void addToDrawer(Component component);

    void addToDrawerFooter(Component component);

    void addToTopHeader(Component component);

    void addToTop(Component component);

    void addToTopFooter(Component component);

    void addNavigationHeaderElement(AbstractNavigationElement component);

    void addNavigationFooterElement(AbstractNavigationElement component);

    void addNavigationElement(AbstractNavigationElement component);

    void addAppBarElement(Component component);

    void setDesign(AppBarDesign design);

    Layout getAppBar();

    HorizontalLayout getAppBarElementWrapper();

    Component getTitleComponent();

    void setTitle(String title);

    void setTitleComponent(Component titleComponent);

    Layout getTitleWrapper();

    Panel getContentHolder();

    VerticalLayout getMenuElementHolder();

    VerticalLayout getMenuFooterHolder();

    VerticalLayout getMenuHeaderHolder();

    Layout getMenuHolder();

    void addAppBarIcon(Component appBarIconComponent);

    void setNavigatorNavigationElements(List<NavigatorNavigationElement> list);

    void refreshNavigationElementInfo();

    ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> getDrawerNavigationElementProvider();

    void setDrawerNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider);

    ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> getTopNavigationElementProvider();

    void setTopNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider);

    ComponentProvider<Component, SectionNavigationElement> getDrawerSectionElementProvider();

    void setDrawerSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider);

    ComponentProvider<Component, SectionNavigationElement> getTopSectionElementProvider();

    void setTopSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider);

    ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> getDrawerSubmenuElementProvider();

    void setDrawerSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider);

    ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> getTopSubmenuElementProvider();

    void setTopSubmenuElementProvider(ComponentProvider<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider);

    ComponentProvider<Component, ClickableNavigationElement> getDrawerClickableElementProvider();

    void setDrawerClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> provider);

    ComponentProvider<Component, ClickableNavigationElement> getTopClickableElementProvider();

    void setTopClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> provider);


}
