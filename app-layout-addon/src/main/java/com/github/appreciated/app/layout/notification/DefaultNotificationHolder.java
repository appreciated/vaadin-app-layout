package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

/**
 * This class is the default implementation of {@link NotificationHolder}
 */

public class DefaultNotificationHolder extends NotificationHolder<DefaultNotification> {

    private Function<DefaultNotification, String> dateTimeFormatter = notification -> new PrettyTime().
            format(Date.from(notification.getCreationTime().atZone(ZoneId.systemDefault()).
                    toInstant()));

    public DefaultNotificationHolder(NotificationClickListener<DefaultNotification> listener) {
        super(listener);
    }

    public DefaultNotificationHolder(DefaultNotification... notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(Collection<DefaultNotification> notifications) {
        super(notifications);
    }

    public DefaultNotificationHolder(NotificationClickListener<DefaultNotification> listener, DefaultNotification... notifications) {
        super(listener, notifications);
    }

    public DefaultNotificationHolder(NotificationClickListener<DefaultNotification> listener, Collection<DefaultNotification> notifications) {
        super(listener, notifications);
    }

    @Override
    public void addNotification(DefaultNotification notification) {
        super.addNotification(notification);
    }

    @Override
    PairComponentFactory<NotificationHolder<DefaultNotification>, DefaultNotification> getComponentProvider() {
        return new DefaultNotificationComponentFactory<>();
    }

    @Override
    public Function<DefaultNotification, String> getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    @Override
    public void setDateTimeFormatter(Function<DefaultNotification, String> formatter) {
        this.dateTimeFormatter = formatter;
    }
}
