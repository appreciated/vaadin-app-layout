package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.component.appbar.IconBadgeButton;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.notification.NotificationsChangeListener;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.github.appreciated.papermenubutton.HorizontalAlignment;
import com.github.appreciated.papermenubutton.PaperMenuButton;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */

@HtmlImport("frontend://src/com/github/appreciated/app-layout/styles/app-bar-notification-button-style.html")
public class AppBarNotificationButton<T extends Notification> extends PaperMenuButton {

    private NotificationsView notificationsView;

    public AppBarNotificationButton(VaadinIcon icon, NotificationHolder<T> holder) {
        super(new IconBadgeButton(icon), new Div());
        this.notificationsView = new NotificationsView(holder);
        this.setContentComponent(notificationsView);
        setClassName("app-bar-notification-button");
        getStyle().set("--paper-menu-button-dropdown-background", "transparent")
                .set("--shadow-elevation-2dp_-_box-shadow", "0px")
                .set("--paper-menu-button-dropdown_-_margin-top", "var(--app-layout-bar-height)")
                .set("--paper-menu-button-dropdown_-_margin-right", "7px");

        setHorizontalAlignment(HorizontalAlignment.RIGHT);
        holder.addNotificationsChangeListener(new NotificationsChangeListener<T>() {
            @Override
            public void onNotificationChanges(NotificationHolder<T> holder) {
                refreshNotifications();
            }

            @Override
            public void onNotificationAdded(T notification) {
                if (!isOpened()) {
                    NotificationViewWithoutWrapper view = new NotificationViewWithoutWrapper<>(notification, holder);
                    view.setWidth("200px");
                    com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(view);
                    notificationView.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_END);
                    notificationView.setDuration(2000);
                    notificationView.open();
                }
                refreshNotifications();
            }

            @Override
            public void onNotificationRemoved(T notification) {

            }
        });
        holder.addClickListener(newStatus -> getUI().ifPresent(ui -> ui.access(AppBarNotificationButton.this::close)));
        holder.bind(((IconBadgeButton) getTriggerComponent()).getBadge());
    }

    public void refreshNotifications() {
        notificationsView.initView();
    }

    public NotificationsView getNotificationsView() {
        return notificationsView;
    }
}
