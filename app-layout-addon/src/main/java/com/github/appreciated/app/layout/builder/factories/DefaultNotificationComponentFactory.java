package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.component.RoundImage;
import com.github.appreciated.app.layout.notification.Notification;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, Notification> {
    @Override
    public Component getComponent(NotificationHolder holder, Notification notification) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.addStyleName("ripple"); // for material theme
        wrapper.setSpacing(false);
        Label timeAgo = new Label(notification.getTimeAgo());
        timeAgo.addStyleName(Styles.APP_BAR_NOTIFICATION_TIME);
        Label title = new Label(notification.getTitle());
        title.addStyleName(Styles.APP_BAR_NOTIFICATION_TITLE);
        Label description = new Label(notification.getDescription());
        description.setWidth(100, Sizeable.Unit.PERCENTAGE);
        description.addStyleName(Styles.APP_BAR_NOTIFICATION_DESCRIPTION);
        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);
        descriptionWrapper.setWidth(100, Sizeable.Unit.PERCENTAGE);
        if (notification.getImage() != null) {
            RoundImage image = new RoundImage(notification.getImage());
            descriptionWrapper.addComponent(image);
            descriptionWrapper.setExpandRatio(description, 1.0f);
        }
        if (notification.isRead()) {
            wrapper.addStyleName(Styles.APP_BAR_NOTIFICATION_READ);
        }
        wrapper.addComponent(new HorizontalLayout(new HorizontalLayout(title, timeAgo)));
        wrapper.addComponent(descriptionWrapper);
        wrapper.addLayoutClickListener(layoutClickEvent -> {
            notification.setRead(true);
            holder.onNotificationClicked(notification);
            wrapper.addStyleName(Styles.APP_BAR_NOTIFICATION_READ);
        });
        wrapper.addStyleName(notification.getStyle());
        return wrapper;
    }
}
