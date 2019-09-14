package com.github.appreciated.app.layout.test.base;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.addons.notification.entity.Priority;
import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

import static com.github.appreciated.app.layout.addons.notification.entity.Priority.MEDIUM;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@StyleSheet("styles.css")
public abstract class AbstractLeftBehaviorView extends AppLayoutRouterLayout {
    DefaultNotificationHolder notificationHolder;
    DefaultBadgeHolder badgeHolder;
    private Behaviour variant;
    private Thread currentThread;

    public abstract Behaviour getVariant();

    protected Class<? extends Component> getViewForI(int i) {
        return getViews()[i - 1];
    }

    public abstract Class<? extends Component>[] getViews();

    public void reloadNotifications() {
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
        notificationHolder.add(new DefaultNotification("Title" + badgeHolder.getCount(), "Description ..............................................." + badgeHolder.getCount(), priority));
    }

    public void furtherConfiguration(AppLayoutBuilder builder) {
    }
}
