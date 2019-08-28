package com.github.appreciated.app.layout.addons.notification.interfaces;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;

public interface NotificationsChangeListener<T extends Notification> {
    default void onNotificationChanges(NotificationHolder<T> holder) {
    }

    default void onNotificationAdded(T notification) {
    }

    default void onNotificationRemoved(T notification) {
    }
}
