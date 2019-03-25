package com.github.appreciated.example.theme;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(Material.class)
public class MainAppLayout extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder();

    public MainAppLayout() {
        notifications.addClickListener(notification -> {/* ... */});

        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));

        Component appBar = AppBarBuilder
                .get()
                .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                .build();

        NavigationElementContainer appMenu = LeftAppMenuBuilder
                .get()
                .addToSection(new LeftHeaderItem("Menu-Header",
                        "Version 2.0.8",
                        "/frontend/images/logo.png"
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
                .add(new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View7.class))
                .build();

        init(AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("App Layout")
                .withAppBar(appBar)
                .withAppMenu(appMenu)
                .build());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        /**
         * Using the @Theme Annotation to set the Dark Theme will cause some issues with shadows which will appear in
         * the wrong color making them seemingly invisible instead do it the following way as long as the issue is not
         * solved see here -> https://github.com/vaadin/flow/issues/4765
         */
        getUI().get().getPage().executeJavaScript("document.documentElement.setAttribute(\"theme\",\"dark\")");
    }
}
