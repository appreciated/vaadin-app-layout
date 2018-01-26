package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.PairComponentProvider;
import com.github.appreciated.app.layout.component.RoundImage;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.io.Serializable;

public class DefaultNotificationComponentProvider implements PairComponentProvider<NotificationHolder, DefaultNotification>, Serializable {
    @Override
    public Component getComponent(NotificationHolder holder, DefaultNotification info) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.addStyleName("ripple"); // for material theme
        wrapper.setSpacing(false);
        Label timeAgo = new Label(info.getTimeAgo());
        timeAgo.addStyleName(Styles.APP_BAR_NOTIFICATION_TIME);
        Label title = new Label(info.getTitle());
        title.addStyleName(Styles.APP_BAR_NOTIFICATION_TITLE);
        Label description = new Label(info.getDescription());
        description.setWidth(100, Sizeable.Unit.PERCENTAGE);
        description.addStyleName(Styles.APP_BAR_NOTIFICATION_DESCRIPTION);
        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);
        descriptionWrapper.setWidth(100, Sizeable.Unit.PERCENTAGE);
        if (info.getImage() != null) {
            RoundImage image = new RoundImage(info.getImage());
            descriptionWrapper.addComponent(image);
            descriptionWrapper.setExpandRatio(description, 1.0f);
        }
        if (!info.isUnnread()) {
            wrapper.addStyleName(Styles.APP_BAR_NOTIFICATION_READ);
        }
        wrapper.addComponent(new HorizontalLayout(new HorizontalLayout(title, timeAgo)));
        wrapper.addComponent(descriptionWrapper);
        wrapper.addLayoutClickListener(layoutClickEvent -> {
            info.setUnread(false);
            holder.onNotificationClicked(info);
            wrapper.addStyleName(Styles.APP_BAR_NOTIFICATION_READ);
        });
        wrapper.addStyleName(info.getStyle());
        return wrapper;
    }
}
