package com.github.appreciated.example.theme;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@HtmlImport("frontend://theming/custom.html") // You can use HTML Imports to manipulate f.e. the accent color
/**
 * !Don't use the @Theme annotation! see details and alternative approach in the the "onAttach" method.
 */
// @Theme(value = Lumo.class, variant = Lumo.DARK)
public class MainAppLayout extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;

    @Override
    public AppLayout createAppLayoutInstance() {

        notifications = new DefaultNotificationHolder(newStatus -> {
        });

        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));
        notifications.addNotification(new DefaultNotification("Test1", "Test2"));

        badge = new DefaultBadgeHolder();

        Component appBar = AppBarBuilder
                .get()
                .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                .build();

        NavigationElementContainer appMenu = LeftAppMenuBuilder
                .get()
                .addToSection(new MenuHeaderComponent("Menu-Header",
                        "Version 2.0.2",
                        "/frontend/images/logo.png"
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
                                View5.class
                        ))
                        .add(new LeftNavigationComponent("More1", VaadinIcon.COG.create(), View6.class))
                        .build())
                .add(new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(), View7.class))
                .build();

        return AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("App Layout")
                .withAppBar(appBar)
                .withAppMenu(appMenu)
                .build();
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
