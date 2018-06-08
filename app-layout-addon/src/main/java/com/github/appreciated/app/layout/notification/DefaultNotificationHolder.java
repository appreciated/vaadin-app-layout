package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.factories.DefaultNotificationComponentFactory;
import com.github.appreciated.app.layout.component.NotificationViewWithoutWrapper;

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
        NotificationViewWithoutWrapper view = new NotificationViewWithoutWrapper(notification);
        view.setWidth("200px");
        com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(view);
        notificationView.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_END);
        notificationView.setDuration(5000);
        notificationView.open();
    }
}
