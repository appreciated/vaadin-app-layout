package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
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

    void setDesign(AppLayoutDesign design);

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

    ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getDrawerNavigationElementProvider();

    void setDrawerNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider);

    ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getTopNavigationElementProvider();

    void setTopNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider);

    ComponentFactory<Component, SectionNavigationElement> getDrawerSectionElementProvider();

    void setDrawerSectionElementProvider(ComponentFactory<Component, SectionNavigationElement> provider);

    ComponentFactory<Component, SectionNavigationElement> getTopSectionElementProvider();

    void setTopSectionElementProvider(ComponentFactory<Component, SectionNavigationElement> provider);

    ComponentFactory<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> getDrawerSubmenuElementProvider();

    void setDrawerSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider);

    ComponentFactory<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> getTopSubmenuElementProvider();

    void setTopSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuComponent, SubmenuNavigationElement> provider);

    ComponentFactory<Component, ClickableNavigationElement> getDrawerClickableElementProvider();

    void setDrawerClickableElementProvider(ComponentFactory<Component, ClickableNavigationElement> provider);

    ComponentFactory<Component, ClickableNavigationElement> getTopClickableElementProvider();

    void setTopClickableElementProvider(ComponentFactory<Component, ClickableNavigationElement> provider);


}
