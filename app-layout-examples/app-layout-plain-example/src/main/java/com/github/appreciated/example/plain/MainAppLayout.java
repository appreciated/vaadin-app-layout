package com.github.appreciated.example.plain;

import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> {

    public MainAppLayout() {
        LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View6.class);
        DefaultBadgeHolder badge = new DefaultBadgeHolder(5);
        badge.bind(menuEntry.getBadge());

        init(AppLayoutBuilder.get(LeftLayouts.LeftResponsive.class)
                .withTitle("App Layout")
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png"),
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), event -> Notification.show("onClick ..."))
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class),
                                new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class),
                                LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                                        .add(new LeftNavigationItem("Charts", VaadinIcon.SPLINE_CHART.create(), View2.class),
                                                                new LeftNavigationItem("Contact", VaadinIcon.CONNECT.create(), View3.class),
                                                                new LeftNavigationItem("More", VaadinIcon.COG.create(), View4.class))
                                                        .build(),
                                                new LeftNavigationItem("Contact1", VaadinIcon.CONNECT.create(), View3.class),
                                                new LeftNavigationItem("More1", VaadinIcon.COG.create(), View5.class))
                                        .build(),
                                menuEntry
                        )
                        .addToSection(FOOTER,
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ..."))
                        )
                        .build())
                .build());
    }
}
