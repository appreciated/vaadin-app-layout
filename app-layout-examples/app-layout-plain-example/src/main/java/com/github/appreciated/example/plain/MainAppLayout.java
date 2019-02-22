package com.github.appreciated.example.plain;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
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
public class MainAppLayout extends AppLayoutRouterLayout {
    /**
     * Do not initialize here. This will lead to NPEs
     */
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder(newStatus -> {
    });
    private DefaultBadgeHolder badge = new DefaultBadgeHolder(5);

    public MainAppLayout() {

        LeftNavigationComponent menuEntry = new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(), View6.class);
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
                        .addToSection(new MenuHeaderComponent("Menu-Header",
                                "Version 2.0.6",
                                "/frontend/images/logo.png"
                        ), HEADER)
                        .addToSection(new LeftClickableComponent("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), HEADER)
                        .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), View1.class))
                        .add(new LeftNavigationComponent("Grid", VaadinIcon.TABLE.create(), GridTest.class))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationComponent("Charts",
                                                VaadinIcon.SPLINE_CHART.create(),
                                                View2.class
                                        ))
                                        .add(new LeftNavigationComponent("Contact",
                                                VaadinIcon.CONNECT.create(),
                                                View3.class
                                        ))
                                        .add(new LeftNavigationComponent("More",
                                                VaadinIcon.COG.create(),
                                                View4.class
                                        ))
                                        .build())
                                .add(new LeftNavigationComponent("Contact1",
                                        VaadinIcon.CONNECT.create(),
                                        View3.class
                                ))
                                .add(new LeftNavigationComponent("More1", VaadinIcon.COG.create(), View5.class))
                                .build())
                        .add(menuEntry)
                        .addToSection(new LeftClickableComponent("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), FOOTER)
                        .build())
                .build());
    }
}
