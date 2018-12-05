package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.factories.DefaultNotificationComponentFactory;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<DefaultNotification> {

    private Function<Notification, String> dateTimeFormatter = notification -> new PrettyTime().
            format(Date.from(notification.getTime().atZone(ZoneId.systemDefault()).
                    toInstant()));

    public DefaultNotificationHolder() {
        setComponentProvider(new DefaultNotificationComponentFactory());
    }

    public DefaultNotificationHolder(DefaultNotification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<DefaultNotification> notifications) {
        super(notifications);
    }

    @Override
    public Function<Notification, String> getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    @Override
    public void setDateTimeFormatter(Function<Notification, String> formatter) {
        dateTimeFormatter = formatter;
    }

}
