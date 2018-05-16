package com.github.appreciated.app.layout.component.button;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_BAR_BADGE;

import com.github.appreciated.app.layout.notification.NotificationHolder;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class AppBarBadgeButton extends AbsoluteLayout implements NotificationHolder.NotificationListener {

    private final IconButton button;
    private final Label badge;
    private NotificationHolder notificationHolder;

    public AppBarBadgeButton(Resource icon, NotificationHolder notificationHolder) {
        super();
        this.notificationHolder = notificationHolder;
        setWidth("64px");
        setHeight("64px");
        button = new IconButton(icon);
        button.setSizeFull();
        badge = new Label();
        notificationHolder.addStatusListener(this);
        badge.addStyleName(APP_BAR_BADGE);
        addComponent(button);
        addComponent(badge, "right: 0px;");
    }

    public AppBarBadgeButton(Resource icon, NotificationHolder notificationHolder, Button.ClickListener listener) {
        this(icon, notificationHolder);
        button.addClickListener(listener);
    }

    @Override
    public void attach() {
        super.attach();
        refreshNotifications(notificationHolder);
    }

    public IconButton getButton() {
        return button;
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        updateBadgeCount(notificationHolder);
    }

    private void updateBadgeCount(NotificationHolder notificationHolder) {
        if (notificationHolder != null) {
            int unreadNotifications = notificationHolder.getUnreadNotifications();
            if (unreadNotifications > 0) {
                badge.setVisible(true);
                if (unreadNotifications < 10) {
                    badge.setValue(String.valueOf(unreadNotifications));
                } else {
                    badge.setValue("9+");
                }
            } else {
                badge.setVisible(false);
            }
        } else {
            badge.setVisible(false);
        }
    }

    public NotificationHolder getNotificationHolder() {
        return notificationHolder;
    }

    @Override
    public void onNotificationChanges(NotificationHolder newStatus) {
        refreshNotifications(newStatus);
    }

    @Override
    public void onUnreadCountChange(NotificationHolder holder) {
        updateBadgeCount(holder);
    }
}


