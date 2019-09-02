package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationsChangeListener;
import com.github.appreciated.app.layout.component.appbar.ContextMenuAppBarButton;
import com.vaadin.flow.component.icon.VaadinIcon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */

public class AppBarNotificationButton<T extends Notification> extends ContextMenuAppBarButton {

    private NotificationsView notificationsView;

    public AppBarNotificationButton(VaadinIcon icon, NotificationHolder<T> holder) {
        super(icon.create());
        this.notificationsView = new NotificationsView(holder);
        setClassName("app-bar-notification-button");

        holder.addNotificationsChangeListener(new NotificationsChangeListener<T>() {
            @Override
            public void onNotificationChanges(NotificationHolder<T> holder) {
                refreshNotifications();
            }

            @Override
            public void onNotificationAdded(T notification) {
                refreshNotifications();
                if (!AppBarNotificationButton.this.getContextMenu().isOpened()) {
                    DisplayableNotificationView view = new DisplayableNotificationView<>(notification, holder);
                    view.setWidth("200px");
                    com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(view);
                    notificationView.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_END);
                    notificationView.setDuration(2000);
                    notificationView.open();
                }
            }

            @Override
            public void onNotificationRemoved(T notification) {

            }
        });
        holder.bind(getBadge());
    }

    public void refreshNotifications() {
        getContextMenu().removeAll();
        notificationsView.initViews().forEach(this::addItem);
    }

    public NotificationsView getNotificationsView() {
        return notificationsView;
    }
}
