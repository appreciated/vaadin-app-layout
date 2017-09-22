package com.github.appreciated.app.layout.builder.entities;


import com.github.appreciated.app.layout.builder.providers.DefaultNotificationCompontProvider;

import java.util.Collection;

public class DefaultNotificationHolder extends NotificationHolder<DefaultNotification> {

    public DefaultNotificationHolder() {
        setComponentProvider(new DefaultNotificationCompontProvider());
    }

    public DefaultNotificationHolder(DefaultNotification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<DefaultNotification> notifications) {
        super(notifications);
    }

}
