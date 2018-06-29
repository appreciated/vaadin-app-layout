package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.component.NotificationViewWithoutWrapper;
import com.github.appreciated.app.layout.component.NotificationsView;
import com.github.appreciated.app.layout.notification.Notification;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.dropdown.Alignment;
import com.github.appreciated.dropdown.HorizontalAlignment;
import com.github.appreciated.dropdown.IronDropdownWrapper;
import com.github.appreciated.dropdown.VerticalAlignment;
import com.vaadin.flow.component.icon.Icon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class AppBarNotificationButton extends IronDropdownWrapper {

    public AppBarNotificationButton(Icon icon, NotificationHolder holder) {
        super(new AppBarBadgeButton(icon, holder), new NotificationsView(holder), new Alignment(HorizontalAlignment.RIGHT, VerticalAlignment.TOP));
        getDropdown().getElement().getStyle().set("margin-top", "56px").set("margin-right", "-5px");
        getContentWrapper().getElement().getStyle().set("max-height", "unset !important");
        holder.addNotificationsChangeListener(new NotificationHolder.NotificationsChangeListener() {
            @Override
            public void onNotificationChanges(NotificationHolder holder) {
                refreshNotifications(holder);
            }

            @Override
            public void onNotificationAdded(Notification notification) {
                if (!getDropdown().isOpened()) {
                    NotificationViewWithoutWrapper view = new NotificationViewWithoutWrapper(notification);
                    view.setWidth("200px");
                    com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(view);
                    notificationView.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_END);
                    notificationView.setDuration(2000);
                    notificationView.open();
                }
            }

            @Override
            public void onNotificationRemoved(Notification notification) {

            }
        });
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        setContent(new NotificationsView(notificationHolder));
    }
}
