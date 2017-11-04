package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.util.List;

public interface AppLayout extends Component {
    String getStyleName();

    static void toggleDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').toggle();");
    }

    static void openDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').open();");
    }

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

    Label getTitle();

    void setTitle(String title);

    Layout getTitleWrapper();

    Panel getContentHolder();

    VerticalLayout getMenuElementHolder();

    VerticalLayout getMenuFooterHolder();

    VerticalLayout getMenuHeaderHolder();

    Layout getMenuHolder();

    void addAppBarIcon(Component appBarIconComponent);

    static void closeDrawerIfNotPersistent() {
        Page.getCurrent().getJavaScript().execute("if(!document.querySelector('app-drawer').hasAttribute('persistent')){document.querySelector('app-drawer').close();}");
    }

    static void closeDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').close();");
    }

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

    ComponentProvider<Component, SubmenuNavigationElement> getDrawerSubmenuElementProvider();

    void setDrawerSubmenuElementProvider(ComponentProvider<Component, SubmenuNavigationElement> provider);

    ComponentProvider<Component, SubmenuNavigationElement> getTopSubmenuElementProvider();

    void setTopSubmenuElementProvider(ComponentProvider<Component, SubmenuNavigationElement> provider);

    ComponentProvider<Component, ClickableNavigationElement> getDrawerClickableElementProvider();

    void setDrawerClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> provider);

    ComponentProvider<Component, ClickableNavigationElement> getTopClickableElementProvider();

    void setTopClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> provider);


    public enum Position {
        TOP, DRAWER
    }
}
