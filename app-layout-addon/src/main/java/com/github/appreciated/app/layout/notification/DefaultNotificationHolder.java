package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<Notification> {

    private Function<Notification, String> dateTimeFormatter = notification -> new PrettyTime().
            format(Date.from(notification.getCreationTime().atZone(ZoneId.systemDefault()).
                    toInstant()));

    public DefaultNotificationHolder(NotificationClickListener<Notification> listener) {
        super(listener);
    }

    public DefaultNotificationHolder(Notification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<Notification> notifications) {
        super(notifications);
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

    @Override
    public Function<Notification, String> getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    @Override
    public void setDateTimeFormatter(Function<Notification, String> formatter) {
        this.dateTimeFormatter = formatter;
    }
}
