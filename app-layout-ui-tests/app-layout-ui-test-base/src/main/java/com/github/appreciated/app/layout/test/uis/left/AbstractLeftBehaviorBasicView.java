package com.github.appreciated.app.layout.test.uis.left;

import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.vaadin.flow.component.icon.VaadinIcon;

public abstract class AbstractLeftBehaviorBasicView extends AbstractLeftBehaviorView {

    public AbstractLeftBehaviorBasicView() {
        notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        badgeHolder = new DefaultBadgeHolder();
        reloadNotifications();
        LeftNavigationComponent home = new LeftNavigationComponent("View1", VaadinIcon.HOME.create(), getViewForI(1));
        LeftNavigationComponent menu = new LeftNavigationComponent("View9", VaadinIcon.MENU.create(), getViewForI(9));
        notificationHolder.bind(home.getBadge());
        badgeHolder.bind(menu.getBadge());
        init(AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppBar(
                        AppBarBuilder.get().add(new AppBarNotificationButton(VaadinIcon.BELL, notificationHolder)).build())
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(new MenuHeaderComponent("App-Layout", "Version 2.0.8", "frontend/images/logo.png"), Section.HEADER)
                                .addToSection(new LeftClickableComponent("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                }), Section.HEADER)
                                .add(home)
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationComponent("View2", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)))
                                        .add(new LeftNavigationComponent("View3", VaadinIcon.CONNECT.create(), getViewForI(3)))
                                        .add(new LeftNavigationComponent("View4", VaadinIcon.COG.create(), getViewForI(4)))
                                        .add(new LeftNavigationComponent("View5", VaadinIcon.CONNECT.create(), getViewForI(5)))
                                        .add(new LeftNavigationComponent("View6", VaadinIcon.COG.create(), getViewForI(6)))
                                        .build())
                                .add(new LeftNavigationComponent("View7", VaadinIcon.COG.create(), getViewForI(7)))
                                .add(new LeftNavigationComponent("View8", VaadinIcon.COG.create(), getViewForI(8)))
                                .add(menu)
                                .build()
                ).build());
    }
}
