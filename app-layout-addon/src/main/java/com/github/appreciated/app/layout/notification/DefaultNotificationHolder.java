package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.notification.entitiy.Notification;

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
