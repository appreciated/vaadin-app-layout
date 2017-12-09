package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.providers.DefaultNotificationComponentProvider;

import java.util.Collection;

/**
 * This Class is the controller of a Component that can display a notification numbers
 */

public class DefaultNotificationHolder extends NotificationHolder<DefaultNotification> {

    public DefaultNotificationHolder() {
        setComponentProvider(new DefaultNotificationComponentProvider());
    }

    public DefaultNotificationHolder(DefaultNotification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<DefaultNotification> notifications) {
        super(notifications);
    }

}
