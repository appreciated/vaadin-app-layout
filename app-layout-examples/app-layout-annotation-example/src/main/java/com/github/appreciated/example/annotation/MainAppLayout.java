package com.github.appreciated.example.annotation;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
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

    public MainAppLayout() {
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        notifications.addClickListener(notification -> {/* ... */});
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(HEADER, new LeftHeaderItem("Menu-Header", "Version 4.0.0",
                                        "/frontend/images/logo.png"),
                                new LeftClickableItem("Clickable Entry",
                                        VaadinIcon.COG.create(),
                                        clickEvent -> Notification.show("onClick ...")
                                ))
                        .add(new LeftNavigationItem(View1.class),
                                LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftSubMenuBuilder
                                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                                        .add(
                                                                new LeftNavigationItem(View2.class),
                                                                new LeftNavigationItem(View3.class),
                                                                new LeftNavigationItem(View4.class))
                                                        .build(),
                                                new LeftNavigationItem(View3.class),
                                                new LeftNavigationItem(View4.class))
                                        .build(),
                                new LeftNavigationItem(View5.class))
                        .addToSection(FOOTER, new LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ))
                        .build())
                .build());
    }
}
