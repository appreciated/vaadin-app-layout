package com.github.appreciated.app.layout.addons.notification;

import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.component.builder.interfaces.PairComponentFactory;
import com.vaadin.flow.server.Command;
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

    /**
     * Adds a notification to {@link NotificationHolder} this method must be invoked from the UI thread, this can be done by using {@link com.vaadin.flow.component.UI#access(Command)}. Also the {@link com.vaadin.flow.component.page.Push} annotation must be added to the {@link com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout}
     *
     * @param notification the instance of the {@link Notification} that is being added
     */
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
