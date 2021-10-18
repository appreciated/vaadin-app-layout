package com.github.appreciated.example.theme;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftSectionItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(Material.LIGHT)
public class MainAppLayout extends AppLayoutRouterLayout {

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

        LeftNavigationItem home = new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class);
        LeftNavigationItem menu = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View8.class);

        Component appMenu = LeftAppMenuBuilder.get()
                .addToSection(HEADER,
                        new LeftHeaderItem("App-Layout", "Version 4.0.0", "/frontend/images/logo.png"),
                        new LeftClickableItem("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                        })
                )
                .add(home,
                        new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class),
                        LeftSubMenuBuilder
                                .get("My Submenu 1", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder.get("My Submenu 2", VaadinIcon.PLUS.create())
                                                .add(new LeftNavigationItem("Charts", VaadinIcon.SPLINE_CHART.create(), View2.class),
                                                        new LeftNavigationItem("Contact", VaadinIcon.CONNECT.create(), View3.class),
                                                        new LeftNavigationItem("More", VaadinIcon.COG.create(), View4.class))
                                                .build(),
                                        new LeftNavigationItem("Contact1", VaadinIcon.CONNECT.create(), View5.class),
                                        new LeftNavigationItem("More1", VaadinIcon.COG.create(), View6.class))
                                .build(),
                        new LeftSectionItem(),
                        LeftSubMenuBuilder
                                .get("My Submenu 3")
                                .add(new LeftNavigationItem("Contact2",
                                        VaadinIcon.CONNECT.create(),
                                        View7.class
                                ))
                                .build(),
                        new LeftSectionItem("Test"),
                        menu)
                .addToSection(FOOTER,
                        new LeftClickableItem("Set Behaviour FOOTER", VaadinIcon.COG.create(), clickEvent -> {
                        })
                )
                .build();

        init(AppLayoutBuilder
                .get(LeftLayouts.LeftResponsive.class)
                .withTitle("App Layout")
                .withAppBar(appBar)
                .withAppMenu(appMenu)
                .build());
    }
}
