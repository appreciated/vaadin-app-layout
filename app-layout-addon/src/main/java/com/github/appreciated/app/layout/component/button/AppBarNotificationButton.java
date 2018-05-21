package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.component.NotificationsView;
import com.github.appreciated.app.layout.webcomponents.irondropdown.IronDropDownWrapper;
import com.vaadin.flow.component.icon.Icon;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class AppBarNotificationButton extends IronDropDownWrapper {

    Factory<NotificationsView, NotificationHolder> factory;

    public AppBarNotificationButton(Icon icon, NotificationHolder holder) {
        super();
        setButton(new AppBarBadgeButton(icon, holder));
        refreshNotifications(holder);
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        setContent(new NotificationsView(notificationHolder));
    }
}
