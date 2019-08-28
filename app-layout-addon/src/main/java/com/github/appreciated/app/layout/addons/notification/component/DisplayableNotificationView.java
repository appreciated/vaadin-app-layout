package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationListener;
import com.github.appreciated.app.layout.component.menu.RoundImage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class DisplayableNotificationView<T extends Notification> extends Div {

    public DisplayableNotificationView(T info, NotificationHolder<T> holder) {
        this(info, holder, null);
    }

    public DisplayableNotificationView(T info, NotificationHolder<T> holder, NotificationListener notificationListener) {
        getElement().getStyle().set("width", "100%").set("color", "#000000");

        Label title = new Label(info.getTitle());
        title.getElement().getStyle()
                .set("font-size", "15px")
                .set("font-weight", "500");

        Label dot = new Label("·");
        dot.getElement().getStyle()
                .set("margin-left", "5px");

        Label timeAgo = new Label(holder.getDateTimeFormatter().apply(info));
        timeAgo.getElement().getStyle()
                .set("font-size", "13px")
                .set("margin-left", "5px")
                .set("font-weight", "300");

        Label description = new Label(info.getDescription());
        description.setWidth("100%");
        description.getElement().getStyle()
                .set("font-size", "15px")
                .set("font-weight", "400")
                .set("white-space", "nowrap")
                .set("text-overflow", "ellipsis")
                .set("overflow", "hidden");

        HorizontalLayout descriptionWrapper = new HorizontalLayout(description);

        descriptionWrapper.setWidth("100%");
        if (info.getImage() != null) {
            RoundImage image = new RoundImage(info.getImage());
            descriptionWrapper.add(image);
        }
        HorizontalLayout headerLine = new HorizontalLayout(title, dot, timeAgo);
        headerLine.setSpacing(false);
        headerLine.setAlignItems(FlexComponent.Alignment.CENTER);
        add(headerLine);
        add(descriptionWrapper);
        if (notificationListener != null) {
            getElement().addEventListener("click", domEvent -> notificationListener.onClick());
        }
    }
}
