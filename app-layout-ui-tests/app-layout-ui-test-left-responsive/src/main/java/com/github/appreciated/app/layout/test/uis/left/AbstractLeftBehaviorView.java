package com.github.appreciated.app.layout.test.uis.left;

import com.github.appreciated.app.layout.test.view.*;
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
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.notification.entitiy.Priority;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AbstractLeftBehaviorView extends AppLayoutRouterLayout {
    DefaultNotificationHolder notificationHolder;
    DefaultBadgeHolder badgeHolder;
    private Behaviour variant;
    private Thread currentThread;

    @Override
    public AppLayout getAppLayout() {
        notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        badgeHolder = new DefaultBadgeHolder();
        reloadNotifications();
        LeftNavigationComponent home = new LeftNavigationComponent("View1", VaadinIcon.HOME.create(), View1.class);
        LeftNavigationComponent menu = new LeftNavigationComponent("View9", VaadinIcon.MENU.create(), View9.class);
        notificationHolder.bind(home.getBadge());
        badgeHolder.bind(menu.getBadge());
        return AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppBar(
                        AppBarBuilder.get().add(new AppBarNotificationButton(VaadinIcon.BELL, notificationHolder)).build())
                .withDesign(AppLayoutDesign.MATERIAL)
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(new MenuHeaderComponent("App-Layout", "Version 2.0.0", "frontend/images/logo.png"), Section.HEADER)
                                .addToSection(new LeftClickableComponent("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                }), Section.HEADER)
                                .add(home)
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationComponent("View2", VaadinIcon.SPLINE_CHART.create(), View2.class))
                                        .add(new LeftNavigationComponent("View3", VaadinIcon.CONNECT.create(), View3.class))
                                        .add(new LeftNavigationComponent("View4", VaadinIcon.COG.create(), View4.class))
                                        .add(new LeftNavigationComponent("View5", VaadinIcon.CONNECT.create(), View5.class))
                                        .add(new LeftNavigationComponent("View6", VaadinIcon.COG.create(), View6.class))
                                        .build())
                                .add(new LeftNavigationComponent("View7", VaadinIcon.COG.create(), View6.class))
                                .add(new LeftNavigationComponent("View8", VaadinIcon.COG.create(), View6.class))
                                .add(menu)
                                .build()
                ).build();
    }

    abstract Behaviour getVariant();

    private void reloadNotifications() {
        if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badgeHolder.clearCount();
        notificationHolder.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    //Thread.sleep(5000);
                    getUI().ifPresent(ui -> ui.access(() -> {
                        addNotification(MEDIUM);
                        badgeHolder.increase();
                        badgeHolder.increase();
                    }));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();
    }

    private void addNotification(Priority priority) {
        notificationHolder.addNotification(new DefaultNotification("Title" + badgeHolder.getCount(), "Description ..............................................." + badgeHolder.getCount(), priority));
    }

    private void setDrawerVariant(Behaviour variant) {
        this.variant = variant;
        reloadConfiguration();
    }
}
