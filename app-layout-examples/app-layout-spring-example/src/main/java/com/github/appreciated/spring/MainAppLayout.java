package com.github.appreciated.spring;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainAppLayout extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    private DefaultBadgeHolder badge = new DefaultBadgeHolder(5);

    public MainAppLayout() {
        notifications.addClickListener(notification -> {/* ... */});

        LeftNavigationItem menuEntry = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View6.class);
        badge.bind(menuEntry.getBadge());

        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
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
                                        View3.class
                                ))
                                .add(new LeftNavigationItem("More1", VaadinIcon.COG.create(), View5.class))
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
