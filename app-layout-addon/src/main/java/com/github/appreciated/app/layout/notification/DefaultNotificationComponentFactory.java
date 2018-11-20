package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.notification.component.NotificationView;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.github.appreciated.app.layout.notification.listener.NotificationListener;
import com.vaadin.flow.component.Component;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, Notification> {
    @Override
    public Component getComponent(NotificationHolder holder, Notification info) {
        return new NotificationView(info, new NotificationListener() {
            @Override
            public void onClick() {
                holder.onNotificationClicked(info);
            }

            @Override
            public void onDismiss() {
                holder.onNotificationDismissed(info);
            }
        });
    }
}
