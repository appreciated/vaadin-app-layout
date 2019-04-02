package com.github.appreciated.example.annotation;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
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
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();

    public MainAppLayout() {
        notifications.addClickListener(notification -> {/* ... */});
        init(AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(new LeftHeaderItem("Menu-Header", "Version 2.1.0",
                                "/frontend/images/logo.png"), HEADER)
                        .addToSection(new LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), HEADER)
                        .add(new LeftNavigationItem(View1.class))
                        .add(LeftSubMenuBuilder
                                .get("My Submenu", VaadinIcon.PLUS.create())
                                .add(LeftSubMenuBuilder
                                        .get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationItem(View2.class))
                                        .add(new LeftNavigationItem(View3.class))
                                        .add(new LeftNavigationItem(View4.class))
                                        .build())
                                .add(new LeftNavigationItem(View3.class))
                                .add(new LeftNavigationItem(View4.class))
                                .build())
                        .add(new LeftNavigationItem(View5.class))
                        .addToSection(new LeftClickableItem("Clickable Entry",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("onClick ...")
                        ), FOOTER)
                        .build())
                .build());
    }
}
