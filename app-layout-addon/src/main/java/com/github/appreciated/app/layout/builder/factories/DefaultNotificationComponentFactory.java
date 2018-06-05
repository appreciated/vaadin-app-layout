package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.component.NotificationView;
import com.github.appreciated.app.layout.notification.Notification;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.vaadin.flow.component.Component;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, Notification> {
    @Override
    public Component getComponent(NotificationHolder holder, Notification info) {
        return new NotificationView(info, domEvent -> {
            domEvent.getSource().getStyle().set("border-left", "3px solid transparent");
            if (info.isSticky()) {
                info.setRead(false);
            }
            holder.onNotificationClicked(info);
        });
    }
}
