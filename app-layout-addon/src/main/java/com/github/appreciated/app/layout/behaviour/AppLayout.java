package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
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

    void addNavigationHeaderElement(Component component);

    void addNavigationFooterElement(Component component);

    void addNavigationElement(Component component);

    void addAppBarElement(Component component);

    void setDesign(AppBarDesign design);

    HorizontalLayout getAppBar();

    HorizontalLayout getAppBarElementWrapper();

    Label getTitle();

    void setTitle(String title);

    HorizontalLayout getTitleWrapper();

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

}
