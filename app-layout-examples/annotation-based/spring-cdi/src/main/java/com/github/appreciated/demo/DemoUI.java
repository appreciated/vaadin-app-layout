package com.github.appreciated.demo;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.CDISubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.factories.DefaultSpringNavigationElementInfoProducer;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.Section.HEADER;

@Push(transport = Transport.LONG_POLLING)
@PushStateNavigation
@SpringUI
@Viewport("width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no")
@Theme("demo")
@Title("App Layout Demo")
public class DemoUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    @Autowired
    SpringViewProvider viewProvider;

    //@Autowired
    //SpringNavigator navigator;

    public void init(VaadinRequest request) {
        setContent(AppLayout.getCDIBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                // You can either set the SpringViewProvider
                .withViewProvider(() -> viewProvider)
                // or the SpringNavigator here
                //.withNavigator(components -> {
                //    navigator.init(this, components);
                //    return navigator;
                //})
                //
                // You will need to provide your own NavigationElementInfoProvider when using a different CDI than Vaadin Spring.
                // The same applies if you want to use other Icons for the MenuButtons than Vaadin Icons. Just checkout the default
                // Implementation and provide your own. If you have a good idea how to solve this better just make a pull
                // Request on Github
                .withNavigationElementInfoProducer(new DefaultSpringNavigationElementInfoProducer())
                //
                .withTitle("App Layout Spring CDI Example")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withDesign(AppLayoutDesign.MATERIAL)
                .add(new MenuHeader("Version 0.9.23", new ThemeResource("logo.png")), HEADER)
                .add(badge, View1.class)
                .add(CDISubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
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