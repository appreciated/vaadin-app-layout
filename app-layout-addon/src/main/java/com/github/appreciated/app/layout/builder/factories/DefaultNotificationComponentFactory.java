package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.app.layout.component.RoundImage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DefaultNotificationComponentFactory implements PairComponentFactory<NotificationHolder, DefaultNotification> {
    @Override
    public Component getComponent(NotificationHolder holder, DefaultNotification info) {
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.getElement().getClassList().add("ripple"); // for material theme
        wrapper.setSpacing(false);
        Label timeAgo = new Label(info.getTimeAgo());
        timeAgo.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_TIME);
        Label title = new Label(info.getTitle());
        title.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_TITLE);
        Label description = new Label(info.getDescription());
        description.setWidth("100%");
        description.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_DESCRIPTION);
        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);
        descriptionWrapper.setWidth("100%");
        if (info.getImage() != null) {
            RoundImage image = new RoundImage(info.getImage());
            descriptionWrapper.add(image);
        }
        if (!info.isUnnread()) {
            wrapper.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_READ);
        }
        wrapper.add(new HorizontalLayout(new HorizontalLayout(title, timeAgo)));
        wrapper.add(descriptionWrapper);
        /*wrapper.addLayoutClickListener(layoutClickEvent -> {
            info.setUnread(false);
            holder.onNotificationClicked(info);
            wrapper.getElement().getClassList().add(Styles.APP_BAR_NOTIFICATION_READ);
        });*/
        wrapper.getElement().getClassList().add(info.getStyle());
        return wrapper;
    }
}
