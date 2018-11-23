package com.github.appreciated.example;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.design.AppLayoutDesign;
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

    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;

    @Override
    public com.github.appreciated.app.layout.behaviour.AppLayout getAppLayout() {

        notifications = new DefaultNotificationHolder(newStatus -> {
        });
        badge = new DefaultBadgeHolder();

        return AppLayoutBuilder
                .get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                        .build())
                .withDesign(AppLayoutDesign.MATERIAL)
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(new MenuHeaderComponent("Menu-Header",
                                "Version 2.0.1",
                                "/frontend/images/logo.png"
                        ), HEADER)
                        .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), View1.class))
                        .add(new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(), View2.class))
                        .withStickyFooter()
                        .addToSection(new LeftClickableComponent("Footer Clickable!",
                                VaadinIcon.COG.create(),
                                clickEvent -> Notification.show("Clicked!")
                        ), FOOTER)
                        .build())
                .build();
    }
}
