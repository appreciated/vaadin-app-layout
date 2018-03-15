package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.factories.DefaultNotificationComponentFactory;

import java.util.Collection;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<DefaultNotification> {

    public DefaultNotificationHolder() {
        setComponentProvider(new DefaultNotificationComponentFactory());
    }

    public DefaultNotificationHolder(DefaultNotification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<DefaultNotification> notifications) {
        super(notifications);
    }

}
