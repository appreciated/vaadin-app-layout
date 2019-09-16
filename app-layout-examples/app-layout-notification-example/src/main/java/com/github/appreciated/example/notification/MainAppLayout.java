package com.github.appreciated.example.notification;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
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
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> {


    public MainAppLayout() {
        DefaultNotificationHolder notifications = new DefaultNotificationHolder();
        notifications.addClickListener(notification -> {/* Use the listener to react on the click on the notification */});
        notifications.add(
                new DefaultNotification("Header1", "Very long description 1"),
                new DefaultNotification("Header2", "Very long description 2"),
                new DefaultNotification("Header3", "Very long description 3"),
                new DefaultNotification("Header4", "Very long description 4")
        );

        init(AppLayoutBuilder.get(LeftLayouts.LeftResponsive.class)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png"),
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), event -> Notification.show("onClick ..."))
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class))
                        .addToSection(FOOTER,
                                new LeftClickableItem("Clickable Entry", VaadinIcon.COG.create(), clickEvent -> Notification.show("onClick ..."))
                        )
                        .build())
                .build());
    }
}
