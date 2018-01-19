package com.github.appreciated.demo;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayout;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.providers.DefaultNavigationElementInfoProvider;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
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

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    public void init(VaadinRequest request) {
        setContent(AppLayout.getDefaultBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout")
                .addToAppBar(new AppBarNotificationButton(notifications))
                .withNavigationElementInfoProvider(new DefaultNavigationElementInfoProvider())
                .withDesign(AppBarDesign.MATERIAL)
                .add(new MenuHeader("Version 0.9.20", new ThemeResource("logo.png")), HEADER)
                .add(badge, View1.class)
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add(View2.class)
                        .add(View3.class)
                        .add(View4.class)
                        .build())
                .add(View5.class)
                .add(View6.class)
                .build());
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

}