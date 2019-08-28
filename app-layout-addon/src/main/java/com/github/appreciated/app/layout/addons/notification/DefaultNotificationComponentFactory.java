package com.github.appreciated.app.layout.addons.notification;

import com.github.appreciated.app.layout.addons.notification.component.NotificationView;
import com.github.appreciated.app.layout.addons.notification.entity.Notification;
import com.github.appreciated.app.layout.addons.notification.listener.NotificationListener;
import com.github.appreciated.app.layout.component.builder.interfaces.PairComponentFactory;
import com.vaadin.flow.component.Component;

public class DefaultNotificationComponentFactory<T extends Notification> implements PairComponentFactory<NotificationHolder<T>, T> {
    @Override
    public Component getComponent(NotificationHolder<T> holder, T info) {
        return new NotificationView<>(info, holder, new NotificationListener() {
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
