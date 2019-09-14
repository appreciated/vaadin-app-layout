package com.github.appreciated.example.theme;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@CssImport("./theming/custom-lumo.css") // You can use css CssImport to manipulate the lumo theme
@CssImport(value = "./theming/custom-app-layout.css", id = "my-custom-layout", themeFor = "app-layout-left-hybrid")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftHybrid> {

    public MainAppLayout() {
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        notifications.addClickListener(notification -> {/* ... */});

        notifications.add(new DefaultNotification("Test1", "Test1"),
                new DefaultNotification("Test2", "Test2"),
                new DefaultNotification("Test3", "Test3"),
                new DefaultNotification("Test4", "Test4")
        );

        Component appBar = AppBarBuilder.get()
                .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                .build();

        Component appMenu = LeftAppMenuBuilder
                .get()
                .addToSection(HEADER,
                        new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png")
                )
                .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class),
                        new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class),
                        LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                                .get("My Submenu", VaadinIcon.PLUS.create())
                                                .add(new LeftNavigationItem("Charts", VaadinIcon.SPLINE_CHART.create(), View2.class),
                                                        new LeftNavigationItem("Contact", VaadinIcon.CONNECT.create(), View3.class),
                                                        new LeftNavigationItem("More", VaadinIcon.COG.create(), View4.class))
                                                .build(),
                                        new LeftNavigationItem("Contact1", VaadinIcon.CONNECT.create(), View5.class))
                                .add(new LeftNavigationItem("More1", VaadinIcon.COG.create(), View6.class))
                                .build(),
                        new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View7.class))
                .build();

        init((LeftLayouts.LeftHybrid) AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("App Layout")
                .withAppBar(appBar)
                .withAppMenu(appMenu)
                .build());
    }
}
