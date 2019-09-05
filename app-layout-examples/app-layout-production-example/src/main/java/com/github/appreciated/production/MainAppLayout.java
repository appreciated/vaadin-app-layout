package com.github.appreciated.production;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
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
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> {

    public MainAppLayout() {
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        notifications.addClickListener(notification -> System.out.println(notification.getTitle()));
        notifications.addNotification(new DefaultNotification("title","description"));

        LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View7.class);
        DefaultBadgeHolder badge = new DefaultBadgeHolder(5);
        badge.bind(menuEntry.getBadge());

        init((LeftLayouts.LeftResponsive) AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton<>(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(new LeftHeaderItem("Menu-Header",
                                "Version 3.0.0",
                                "/frontend/images/logo.png"
                        ), HEADER)
                        .addToSection(new LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), HEADER)
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class))
                        .add(new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationItem("Charts",
                                                VaadinIcon.SPLINE_CHART.create(),
                                                View2.class
                                        ))
                                        .add(new LeftNavigationItem("Contact",
                                                VaadinIcon.CONNECT.create(),
                                                View3.class
                                        ))
                                        .add(new LeftNavigationItem("More",
                                                VaadinIcon.COG.create(),
                                                View4.class
                                        ))
                                        .build())
                                .add(new LeftNavigationItem("Contact1",
                                        VaadinIcon.CONNECT.create(),
                                        View5.class
                                ))
                                .add(new LeftNavigationItem("More1", VaadinIcon.COG.create(), View6.class))
                                .build())
                        .add(menuEntry)
                        .addToSection(new LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), FOOTER)
                        .build())
                .build());
    }

}
