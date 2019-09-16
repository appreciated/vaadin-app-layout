package com.github.appreciated.app.layout.test.base;

import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;

public abstract class AbstractLeftBehaviorBasicView extends AbstractLeftBehaviorView {

    private final FlexLayout appBar;

    public AbstractLeftBehaviorBasicView() {
        badgeHolder = new DefaultBadgeHolder();
        LeftNavigationItem home = new LeftNavigationItem("View1", VaadinIcon.HOME.create(), getViewForI(1));
        LeftNavigationItem menu = new LeftNavigationItem("View9", VaadinIcon.MENU.create(), getViewForI(9));
        badgeHolder.bind(menu.getBadge());

        appBar = AppBarBuilder.get()
                .add(new IconButton(VaadinIcon.EDIT.create(), event -> Notification.show("IconButton clicked")))
                .build();

        AppLayoutBuilder builder = AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppBar(appBar)
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(Section.HEADER,
                                        new LeftHeaderItem("App-Layout", "Version 4.0.0", "frontend/images/logo.png"),
                                        new LeftClickableItem("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                        })
                                )
                                .add(home,
                                        LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                                .add(new LeftNavigationItem("View2", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)),
                                                        new LeftNavigationItem("View3", VaadinIcon.CONNECT.create(), getViewForI(3)),
                                                        new LeftNavigationItem("View4", VaadinIcon.COG.create(), getViewForI(4)),
                                                        new LeftNavigationItem("View5", VaadinIcon.CONNECT.create(), getViewForI(5)),
                                                        new LeftNavigationItem("View6", VaadinIcon.COG.create(), getViewForI(6)))
                                                .build(),
                                        new LeftNavigationItem("View7", VaadinIcon.COG.create(), getViewForI(7)),
                                        new LeftNavigationItem("View8", VaadinIcon.COG.create(), getViewForI(8)),
                                        menu)
                                .build()
                );
        furtherConfiguration(builder);
        init(builder.build());
    }

    public FlexLayout getAppBar() {
        return appBar;
    }
}
