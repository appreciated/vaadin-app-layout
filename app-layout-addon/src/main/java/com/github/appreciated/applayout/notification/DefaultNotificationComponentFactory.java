package com.github.appreciated.applayout.notification;

import com.github.appreciated.applayout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.applayout.notification.component.NotificationView;
import com.github.appreciated.applayout.notification.entitiy.Notification;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, Notification> {
    @Override
    public Component getComponent(NotificationHolder holder, Notification info) {
        return new NotificationView(info, domEvent -> {
            new Thread(() -> {
                UI.getCurrent().access(() -> {
                    if (!info.isSticky()) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        domEvent.getSource().getStyle().set("border-left", "3px solid transparent !important");
                        info.setRead(false);
                    }
                });
            }).start();
            holder.onNotificationClicked(info);
        });
    }
}
