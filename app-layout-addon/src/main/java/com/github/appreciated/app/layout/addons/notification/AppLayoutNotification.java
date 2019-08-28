package com.github.appreciated.app.layout.addons.notification;

import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.session.UIAttributes;
import com.vaadin.flow.component.UI;

import java.util.Optional;

public class AppLayoutNotification {
    public static <T extends Notification> void show(T notification) {
        getNotificationHolder().ifPresent(holder ->
                UI.getCurrent().access(() -> holder.addNotification(notification))
        );
    }

    public static <T extends Notification> Optional<NotificationHolder<T>> getNotificationHolder() {
        return Optional.ofNullable(UIAttributes.get(NotificationHolder.class));
    }

    public static void setNotificationHolder(NotificationHolder holder) {
        UIAttributes.set(NotificationHolder.class, holder);
    }
}
