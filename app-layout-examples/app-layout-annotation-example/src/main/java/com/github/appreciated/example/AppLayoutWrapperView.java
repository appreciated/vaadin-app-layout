package com.github.appreciated.example;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.design.AppLayoutDesign;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;
import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

/**
 * The AppLayoutWrapperView contains the Menu your Views will be wrapped in. You need to reference this view in the Other views inside the @Route(value = ..., layout = AppLayoutWrapperView.class) Annotation
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class AppLayoutWrapperView extends AppLayoutRouterLayout {

    DefaultNotificationHolder notifications;

    @Override
    public AppLayout getAppLayout() {
        initNotifications();

        return AppLayoutBuilder.get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout")
                .withAppBar(
                        AppBarBuilder.get().add(new AppBarNotificationButton(VaadinIcon.BELL, notifications)).build())
                .withDesign(AppLayoutDesign.MATERIAL)
                .withAppMenu( // Do not use the same Route twice the will lead to some weird effects like two highlighted elements in the menu.
                        LeftAppMenuBuilder.get()
                                .addToSection(new MenuHeaderComponent("App-Layout", "Version 2.0.0", "frontend/images/logo.png"), HEADER)
                                .addToSection(new LeftClickableComponent("Click Me Please", VaadinIcon.COG.create(), clickEvent -> Notification.show("You can set this Event as you like")), HEADER)
                                .add(new LeftNavigationComponent(View1.class))
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                                .add(new LeftNavigationComponent(View2.class))
                                                .add(new LeftNavigationComponent(View3.class))
                                                .add(new LeftNavigationComponent(View4.class))
                                                .build())
                                        .add(new LeftNavigationComponent(View3.class))
                                        .add(new LeftNavigationComponent(View4.class))
                                        .build())
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                                .add(new LeftNavigationComponent(View2.class))
                                                .add(new LeftNavigationComponent(View3.class))
                                                .add(new LeftNavigationComponent(View4.class))
                                                .build())
                                        .add(new LeftNavigationComponent(View3.class))
                                        .add(new LeftNavigationComponent(View4.class))
                                        .build())
                                .add(new LeftNavigationComponent(View5.class))
                                .addToSection(new LeftClickableComponent("Click Me Please", VaadinIcon.COG.create(), clickEvent -> Notification.show("You can set this Event as you like")), FOOTER)
                                .build()
                ).build();
    }

    private void initNotifications() {
        notifications = new DefaultNotificationHolder(newStatus -> {
        });
        for (int i = 0; i < 3; i++) {
            notifications.addNotification(new DefaultNotification("Title" + i, "Description" + i, MEDIUM));
        }
    }
}
