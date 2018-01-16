package com.github.appreciated.app.layout.component.window;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 *
 */
public class MaterialNotificationWindow extends NotificationWindow {

    private VerticalLayout notificationsView;

    public MaterialNotificationWindow(NotificationHolder holder) {
        super(holder);
        addStyleName("translucent");
    }

    VerticalLayout getNotificationLayout(NotificationHolder notifications) {
        List<Component> components = notifications.getNotifications(isShowAll());
        notificationsView = new VerticalLayout(components.toArray(new Component[]{}));
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_WINDOW);
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);
        notificationsView.setSpacing(true);
        return notificationsView;
    }

    @Override
    public VerticalLayout getNotificationsView() {
        return notificationsView;
    }
}
