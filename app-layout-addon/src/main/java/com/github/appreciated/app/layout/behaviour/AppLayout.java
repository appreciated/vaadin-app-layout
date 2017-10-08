package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.vaadin.ui.*;

public interface AppLayout extends Component {
    String getStyleName();

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

    VerticalLayout getMenuHolder();

    void addAppBarIcon(Component appBarIconComponent);

}
