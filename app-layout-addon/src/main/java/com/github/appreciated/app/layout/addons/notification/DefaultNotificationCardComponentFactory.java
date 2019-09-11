package com.github.appreciated.app.layout.addons.notification;

import com.github.appreciated.app.layout.addons.notification.component.NotificationCardView;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationListener;
import com.github.appreciated.app.layout.component.builder.interfaces.PairComponentFactory;
import com.vaadin.flow.component.Component;

public class DefaultNotificationCardComponentFactory<T extends Notification> implements PairComponentFactory<NotificationHolder<T>, T> {
    @Override
    public Component getComponent(NotificationHolder<T> holder, T info) {
        return new NotificationCardView<>(info, holder, new NotificationListener() {
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
