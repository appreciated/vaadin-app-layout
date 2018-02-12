package com.github.appreciated.demo;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayout;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.AppLayoutConfiguration.Position.HEADER;

@PushStateNavigation // proper url paths
@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
public class DemoUI extends UI {

    public void init(VaadinRequest request) {
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        DefaultBadgeHolder badge = new DefaultBadgeHolder();

        AppLayoutComponent layout = AppLayout.getDefaultBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withNavigator(container -> new Navigator(this, container))
                .withTitle("App Layout Basic Example")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.MATERIAL)
                .withNavigatorConsumer(navigator -> {/* Do something with it */})
                .add(new MenuHeader("Version 0.9.20", new ThemeResource("logo.png")), HEADER)
                .add("Home", VaadinIcons.HOME, badge, View1.class)
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add("Charts", "test", VaadinIcons.SPLINE_CHART, View2.class)
                        .add("Contact", VaadinIcons.CONNECT, View3.class)
                        .add("More", VaadinIcons.COG, View4.class)
                        .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                .build();
        setContent(layout);
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

}