package com.github.appreciated.app.layout.component.window;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

/**
 *
 */
public class MaterialNotificationWindow extends NotificationWindow {

    private VerticalLayout notificationsView;

    public MaterialNotificationWindow(NotificationHolder holder) {
        super(holder);
        getElement().getClassList().add("translucent");
    }

    VerticalLayout getNotificationLayout(NotificationHolder notifications) {
        List<Component> components = notifications.getNotifications(isShowAll());
        notificationsView = new VerticalLayout(components.toArray(new Component[]{}));
        notificationsView.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_WINDOW);
        notificationsView.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);
        notificationsView.setSpacing(true);
        return notificationsView;
    }

    @Override
    public VerticalLayout getNotificationsView() {
        return notificationsView;
    }
}
