package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.factories.DefaultNotificationComponentFactory;

import java.util.Collection;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<Notification> {

    public DefaultNotificationHolder() {
        setComponentProvider(new DefaultNotificationComponentFactory());
    }

    public DefaultNotificationHolder(Notification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<Notification> notifications) {
        super(notifications);
    }

    @Override
    public void addNotification(Notification notification) {
        super.addNotification(notification);
    }
}
