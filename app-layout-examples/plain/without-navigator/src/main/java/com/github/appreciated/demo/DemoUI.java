package com.github.appreciated.demo;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.CDISubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.Section.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
public class DemoUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private ComponentNavigator navigator;

    public void init(VaadinRequest request) {
        setContent(AppLayout.getNoNavigatorBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout No Navigator Plain Example")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withDefaultNavigationView(View1.class)
                .withDesign(AppLayoutDesign.MATERIAL)
                .withNavigatorConsumer(navigator -> this.navigator = navigator)
                .add(new MenuHeader("Version 1.0.0", new ThemeResource("logo.png")), HEADER)
                .add("Home", VaadinIcons.HOME, badge, View1.class)
                .add(CDISubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                        .add("Contact", VaadinIcons.CONNECT, View3.class)
                        .add("More", VaadinIcons.COG, View4.class)
                        .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                .build());
    }

    /**
     * Now you can access the component Navigator by DemoUI.getCurrent().getComponentNavigator() and use it to navigate
     * to different views manually. Note that the ComponentNavigator is a replacement of the Vaadin Navigator and works
     * differently.
     *
     * @return
     */
    public ComponentNavigator getComponentNavigator() {
        return navigator;
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

}