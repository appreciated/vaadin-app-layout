package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.component.NotificationsView;
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
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        setContent(new NotificationsView(notificationHolder));
    }
}
