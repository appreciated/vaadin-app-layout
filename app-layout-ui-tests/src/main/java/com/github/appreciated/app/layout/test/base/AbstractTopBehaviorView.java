package com.github.appreciated.app.layout.test.base;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.addons.notification.entity.Priority;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.top.item.TopClickableItem;
import com.github.appreciated.app.layout.component.menu.top.item.TopNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.addons.notification.entity.Priority.MEDIUM;
import static com.github.appreciated.app.layout.entity.Section.FOOTER;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("styles.css")
public abstract class AbstractTopBehaviorView extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder(newStatus -> {
    });
    private DefaultBadgeHolder badge = new DefaultBadgeHolder();

    private Class<? extends AppLayout> variant;
    private Thread currentThread;

    public AbstractTopBehaviorView() {
        reloadNotifications();
        init(AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(new NotificationButton<>(VaadinIcon.BELL, notifications))
                        .build()
                )
                .withAppMenu(TopAppMenuBuilder.get()
                        .addToSection(Section.HEADER,
                                new TopClickableItem("Set Behaviour 1", VaadinIcon.COG.create(), clickEvent -> {
                                })
                        )
                        .add(new TopNavigationItem("Home", VaadinIcon.HOME.create(), getViewForI(1)),
                                new TopNavigationItem("Contact", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)))
                        .addToSection(FOOTER,
                                new TopClickableItem("Set Behaviour 2", VaadinIcon.COG.create(), clickEvent -> {
                                }),
                                new TopNavigationItem("More", VaadinIcon.CONNECT.create(), getViewForI(3))
                        ).build()
                ).build());
    }

    private void reloadNotifications() {
        if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badge.clearCount();
        notifications.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    getUI().ifPresent(ui -> ui.access(() -> {
                        addNotification(MEDIUM);
                        badge.increase();
                        badge.increase();
                    }));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();
    }

    public abstract Class<? extends AppLayout> getVariant();

    private Class<? extends Component> getViewForI(int i) {
        return getViews()[i - 1];
    }

    private void addNotification(Priority priority) {
        notifications.add(new DefaultNotification("Title" + badge.getCount(), "Description ..............................................." + badge.getCount(), priority));
    }

    public abstract Class<? extends Component>[] getViews();
}