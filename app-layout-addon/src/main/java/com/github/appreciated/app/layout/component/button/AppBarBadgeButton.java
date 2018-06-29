package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.notification.Notification;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.webcomponents.paperbadge.PaperBadge;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

/**
 * A borderless button component which shows an indicator how many new notifications are available in the connected Notification holder.
 */
public class AppBarBadgeButton extends Div {

    static int idCounter = 0;

    private final PaperIconButton button;
    private final PaperBadge badge;
    private NotificationHolder notificationHolder;

    public AppBarBadgeButton(Icon icon, NotificationHolder notificationHolder) {
        this(icon, notificationHolder, null);
    }

    public AppBarBadgeButton(Icon icon, NotificationHolder notificationHolder, ComponentEventListener<ClickEvent<PaperIconButton>> listener) {
        setId("menu-btn-" + idCounter++);
        this.notificationHolder = notificationHolder;
        setWidth("48px");
        setHeight("48px");
        button = new PaperIconButton(icon.getElement().getAttribute("icon"));
        button.getElement().getStyle()
                .set("width", "100%")
                .set("height", "100%");
        button.getElement().setAttribute("id", "button");
        badge = new PaperBadge(this, "0");
        badge.getElement().getStyle()
                .set("margin-top", "6px")
                .set("margin-left", "-6px");

        notificationHolder.addNotificationsChangeListener(new NotificationHolder.NotificationsChangeListener() {
            @Override
            public void onNotificationChanges(NotificationHolder holder) {
                updateBadgeCount(holder);
            }

            @Override
            public void onNotificationAdded(Notification notification) {

            }

            @Override
            public void onNotificationRemoved(Notification notification) {

            }
        });
        add(button);
        add(badge);
        if (listener != null) {
            button.setClickListener(listener);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        refreshNotifications(notificationHolder);
    }

    public PaperIconButton getButton() {
        return button;
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        updateBadgeCount(notificationHolder);
    }

    private void updateBadgeCount(NotificationHolder notificationHolder) {
        getUI().ifPresent(ui -> ui.access(() -> {
            int unreadNotifications = notificationHolder.getUnreadNotifications();
            if (unreadNotifications > 0) {
                badge.setVisible(true);
                if (unreadNotifications < 10) {
                    badge.setLabel(String.valueOf(unreadNotifications));
                } else {
                    badge.setLabel("9+");
                }
            } else {
                badge.setVisible(false);
            }
        }));
    }

    public NotificationHolder getNotificationHolder() {
        return notificationHolder;
    }

}


