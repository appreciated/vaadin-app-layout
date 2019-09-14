package com.github.appreciated.app.layout.test.base;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.icon.VaadinIcon;

public abstract class AbstractLeftBehaviorAnnotationView extends AbstractLeftBehaviorView {

    public AbstractLeftBehaviorAnnotationView() {
        notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        badgeHolder = new DefaultBadgeHolder();
        reloadNotifications();
        LeftNavigationItem home = new LeftNavigationItem(getViewForI(1));
        LeftNavigationItem menu = new LeftNavigationItem(getViewForI(9));
        notificationHolder.bind(home.getBadge());
        badgeHolder.bind(menu.getBadge());
        AppLayoutBuilder builder = AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppBar(
                        AppBarBuilder.get().add(new NotificationButton(VaadinIcon.BELL, notificationHolder)).build())
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(new LeftHeaderItem("App-Layout", "Version 4.0.0", "frontend/images/logo.png"), Section.HEADER)
                                .addToSection(new LeftClickableItem("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                }), Section.HEADER)
                                .add(home)
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationItem(getViewForI(2)))
                                        .add(new LeftNavigationItem(getViewForI(3)))
                                        .add(new LeftNavigationItem(getViewForI(4)))
                                        .add(new LeftNavigationItem(getViewForI(5)))
                                        .add(new LeftNavigationItem(getViewForI(6)))
                                        .build())
                                .add(new LeftNavigationItem(getViewForI(7)))
                                .add(new LeftNavigationItem(getViewForI(8)))
                                .add(menu)
                                .build()
                );
        furtherConfiguration(builder);
        init(builder.build());
    }

    public void furtherConfiguration(AppLayoutBuilder builder) {
    }

}
