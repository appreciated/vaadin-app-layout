package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationListener;
import com.github.appreciated.card.RippleClickableCard;

public class NotificationCardView<T extends Notification> extends RippleClickableCard {

    private final NotificationView<T> notfication;

    public NotificationCardView(T info, NotificationHolder<T> holder, NotificationListener listener) {
        notfication = new NotificationView<>(info, holder, listener, false);
        notfication.setPadding(true);
        setBackground("var(--lumo-base-color)");
        add(notfication);
        setWidthFull();
    }

    public NotificationView<T> getNotfication() {
        return notfication;
    }
}