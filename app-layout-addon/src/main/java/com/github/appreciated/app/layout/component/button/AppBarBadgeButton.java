package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_BAR_BADGE;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class AppBarBadgeButton extends HorizontalLayout implements NotificationHolder.NotificationListener {

    private final IconButton button;
    private final Label badge;
    private NotificationHolder notificationHolder;

    public AppBarBadgeButton(String icon, NotificationHolder notificationHolder) {
        super();
        this.notificationHolder = notificationHolder;
        setWidth("64px");
        setHeight("64px");
        button = new IconButton(icon);
        button.setSizeFull();
        badge = new Label();
        notificationHolder.addStatusListener(this);
        badge.getElement().getClassList().add(APP_BAR_BADGE);
        add(button);
        add(badge);
    }

    public AppBarBadgeButton(String icon, NotificationHolder notificationHolder, ComponentEventListener<ClickEvent<Button>> listener) {
        this(icon, notificationHolder);
        button.addClickListener(listener);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
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
                    badge.setText(String.valueOf(unreadNotifications));
                } else {
                    badge.setText("9+");
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


