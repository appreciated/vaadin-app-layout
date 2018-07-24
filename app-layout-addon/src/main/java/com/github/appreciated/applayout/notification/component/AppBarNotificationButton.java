package com.github.appreciated.applayout.notification.component;

import com.github.appreciated.applayout.component.appbar.IconBadgeButton;
import com.github.appreciated.applayout.notification.NotificationHolder;
import com.github.appreciated.applayout.notification.entitiy.Notification;
import com.github.appreciated.papermenubutton.HorizontalAlignment;
import com.github.appreciated.papermenubutton.PaperMenuButton;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */

@HtmlImport("frontend://com/github/appreciated/app-layout/styles/app-bar-notification-button-style.html")
public class AppBarNotificationButton extends PaperMenuButton {

    public AppBarNotificationButton(Icon icon, NotificationHolder holder) {
        super(new IconBadgeButton(icon), new NotificationsView(holder));
        setClassName("app-bar-notification-button");
        getStyle().set("--paper-menu-button-dropdown-background", "transparent")
                .set("--shadow-elevation-2dp_-_box-shadow", "0px")
                .set("--paper-menu-button-dropdown_-_margin-top", "64px")
                .set("--paper-menu-button-dropdown_-_margin-right", "7px");

        setHorizontalAlignment(HorizontalAlignment.RIGHT);
        holder.addNotificationsChangeListener(new NotificationHolder.NotificationsChangeListener() {
            @Override
            public void onNotificationChanges(NotificationHolder holder) {
                refreshNotifications(holder);
            }

            @Override
            public void onNotificationAdded(Notification notification) {
                if (!isOpened()) {
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
        holder.addClickListener(newStatus -> getUI().ifPresent(ui -> ui.access(() -> AppBarNotificationButton.this.close())));
        holder.bind(((IconBadgeButton) getButton()).getBadge());
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        getUI().ifPresent(ui -> ui.access(() -> {
            setContent(new NotificationsView(notificationHolder));
        }));
    }
}
