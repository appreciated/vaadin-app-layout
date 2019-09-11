package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationsChangeListener;
import com.github.appreciated.app.layout.component.appbar.ComponentBadgeWrapper;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */

public class AppBarNotificationButton<T extends Notification> extends ComponentBadgeWrapper<Button> {

    private final NotificationsOverlayView<T> notificationOverlay;
    private NotificationHolder<T> holder;

    public AppBarNotificationButton() {
        this(VaadinIcon.SEARCH);
    }

    public AppBarNotificationButton(VaadinIcon icon) {
        this(icon.create());
    }

    public AppBarNotificationButton(Component icon) {
        super(new Button(icon));
        getWrappedComponent().addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
        notificationOverlay = new NotificationsOverlayView<>();
        addClickListener(event -> notificationOverlay.open());
    }

    public AppBarNotificationButton(VaadinIcon icon, NotificationHolder<T> holder) {
        this(icon.create());
        this.holder = holder;
        setClassName("app-bar-notification-button");

        holder.addNotificationsChangeListener(new NotificationsChangeListener<T>() {
            @Override
            public void onNotificationChanges(NotificationHolder<T> holder) {
                refreshNotifications();
            }

            @Override
            public void onNotificationAdded(T notification) {
                refreshNotifications();
                if (!notificationOverlay.getOpened()) {
                    com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(holder.getComponent(notification));
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
        notificationOverlay.setHolder(holder);
    }

    public void refreshNotifications() {
        notificationOverlay.refreshNotificationViews();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        notificationOverlay.getElement().removeFromParent();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        attachEvent.getUI().add(notificationOverlay);
    }

    public NotificationsOverlayView<T> getNotificationOverlay() {
        return notificationOverlay;
    }
}
