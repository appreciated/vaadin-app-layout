package com.github.appreciated.app.layout.notification.listener;

import com.github.appreciated.app.layout.notification.entitiy.Notification;

public interface NotificationListener {
    void onClick(Notification notification);

    void onDismiss(Notification notification);

}
