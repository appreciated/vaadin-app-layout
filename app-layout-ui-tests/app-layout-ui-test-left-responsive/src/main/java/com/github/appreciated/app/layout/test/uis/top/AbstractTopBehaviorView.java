package com.github.appreciated.app.layout.test.uis.top;

import com.github.appreciated.app.layout.test.view.View1;
import com.github.appreciated.app.layout.test.view.View2;
import com.github.appreciated.app.layout.test.view.View3;
import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.top.TopClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.top.TopNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.top.builder.TopAppMenuBuilder;
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

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AbstractTopBehaviorView extends AppLayoutRouterLayout {
    DefaultNotificationHolder notificationHolder;
    DefaultBadgeHolder badgeHolder;
    private Behaviour variant;
    private Thread currentThread;

    @Override
    public AppLayout getAppLayout() {
        notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
        badgeHolder = new DefaultBadgeHolder();

        reloadNotifications();
        return AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notificationHolder))
                        .build()
                )
                .withDesign(AppLayoutDesign.MATERIAL)
                .withAppMenu(TopAppMenuBuilder.get()
                        .addToSection(new TopClickableComponent("Set Behaviour 1", VaadinIcon.COG.create(), clickEvent -> {
                        }), Section.HEADER)
                        .add(new TopNavigationComponent("Home", VaadinIcon.HOME.create(), View1.class))
                        .add(new TopNavigationComponent("Contact", VaadinIcon.SPLINE_CHART.create(), View2.class))
                        .addToSection(new TopClickableComponent("Set Behaviour 2", VaadinIcon.COG.create(), clickEvent -> {
                        }), FOOTER)
                        .addToSection(new TopNavigationComponent("More", VaadinIcon.CONNECT.create(), View3.class), FOOTER).build()
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
