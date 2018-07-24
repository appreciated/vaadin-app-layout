package com.github.appreciated.applayout.notification;

import com.github.appreciated.applayout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.applayout.notification.entitiy.Notification;

import java.util.Collection;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<Notification> {

    public DefaultNotificationHolder(NotificationClickListener<Notification> listener) {
        super(listener);
    }

    public DefaultNotificationHolder(NotificationClickListener<Notification> listener, Notification... notifications) {
        super(listener, notifications);
    }

    public DefaultNotificationHolder(NotificationClickListener<Notification> listener, Collection<Notification> notifications) {
        super(listener, notifications);
    }

    @Override
    public void addNotification(Notification notification) {
        super.addNotification(notification);
    }

    @Override
    PairComponentFactory<NotificationHolder, Notification> getComponentProvider() {
        return new DefaultNotificationComponentFactory();
    }
}
