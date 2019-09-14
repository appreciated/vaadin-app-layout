package com.github.appreciated.example.footer;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
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
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsiveHybrid> {

    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    private DefaultBadgeHolder badge = new DefaultBadgeHolder();

    public MainAppLayout() {
        notifications.addClickListener(notification -> {/* ... */});
        init((LeftLayouts.LeftResponsiveHybrid) AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                        .build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Menu-Header", "Version 4.0.0", "/frontend/images/logo.png")
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class),
                                new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View2.class))
                        .withStickyFooter()
                        .addToSection(FOOTER,
                                new LeftClickableItem("Footer Clickable!", VaadinIcon.COG.create(), clickEvent -> Notification.show("Clicked!")))
                        .build())
                .build());
    }

    public DefaultBadgeHolder getBadge() {
        return badge;
    }

    public DefaultNotificationHolder getNotifications() {
        return notifications;
    }
}
