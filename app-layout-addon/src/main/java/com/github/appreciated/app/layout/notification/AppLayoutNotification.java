package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.vaadin.flow.component.UI;

import java.util.Optional;

public class AppLayoutNotification {
    public static void show(Notification notification) {
        getNotificationHolder().ifPresent(holder ->
                UI.getCurrent().access(() -> holder.addNotification(notification))
        );
    }

    public static Optional<NotificationHolder> getNotificationHolder() {
        return Optional.ofNullable(UI.getCurrent().getSession().getAttribute(NotificationHolder.class));
    }

    public static void setNotificationHolder(NotificationHolder holder) {
        UI.getCurrent().getSession().setAttribute(NotificationHolder.class, holder);
    }
}
