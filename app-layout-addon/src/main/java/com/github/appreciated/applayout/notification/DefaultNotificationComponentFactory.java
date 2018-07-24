package com.github.appreciated.applayout.notification;

import com.github.appreciated.applayout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.applayout.notification.component.NotificationView;
import com.github.appreciated.applayout.notification.entitiy.Notification;
import com.vaadin.flow.component.Component;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, Notification> {
    @Override
    public Component getComponent(NotificationHolder holder, Notification info) {
        return new NotificationView(info, domEvent -> holder.onNotificationClicked(info));
    }
}
