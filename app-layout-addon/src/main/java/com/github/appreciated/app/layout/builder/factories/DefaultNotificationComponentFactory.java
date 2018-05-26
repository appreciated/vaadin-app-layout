package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.component.NotificationView;
import com.vaadin.flow.component.Component;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, DefaultNotification> {
    @Override
    public Component getComponent(NotificationHolder holder, DefaultNotification info) {
        return new NotificationView(info, domEvent -> {
            info.setUnread(false);
            holder.onNotificationClicked(info);
        });
    }
}
