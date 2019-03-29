package com.github.appreciated.app.layout.test.uis.left;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.top.TopClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.top.TopNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.entity.Section;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.notification.entitiy.Priority;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("styles.css")
public abstract class AbstractTopBehaviorView extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications = new DefaultNotificationHolder(newStatus -> {
    });
    private DefaultBadgeHolder badge = new DefaultBadgeHolder();

    private Behaviour variant;
    private Thread currentThread;

    public AbstractTopBehaviorView() {
        reloadNotifications();
        init(AppLayoutBuilder.get(getVariant())
                .withTitle("App Layout")
                .withAppBar(AppBarBuilder.get()
                        .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                        .build()
                )
                .withAppMenu(TopAppMenuBuilder.get()
                        .addToSection(new TopClickableComponent("Set Behaviour 1", VaadinIcon.COG.create(), clickEvent -> {
                        }), Section.HEADER)
                        .add(new TopNavigationComponent("Home", VaadinIcon.HOME.create(), getViewForI(1)))
                        .add(new TopNavigationComponent("Contact", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)))
                        .addToSection(new TopClickableComponent("Set Behaviour 2", VaadinIcon.COG.create(), clickEvent -> {
                        }), FOOTER)
                        .addToSection(new TopNavigationComponent("More", VaadinIcon.CONNECT.create(), getViewForI(3)), FOOTER).build()
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

    public abstract Behaviour getVariant();

    private Class<? extends Component> getViewForI(int i) {
        return getViews()[i - 1];
    }

    private void addNotification(Priority priority) {
        notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description ..............................................." + badge.getCount(), priority));
    }

    public abstract Class<? extends Component>[] getViews();
}