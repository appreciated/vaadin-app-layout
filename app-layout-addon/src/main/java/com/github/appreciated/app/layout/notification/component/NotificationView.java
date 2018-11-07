package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.component.appmenu.RoundImage;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.github.appreciated.app.layout.webcomponents.papercard.PaperCard;
import com.github.appreciated.app.layout.webcomponents.paperripple.PaperRipple;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.DomEventListener;

public class NotificationView extends PaperCard {

    public NotificationView(Notification info) {
        this(info, null);
    }

    public NotificationView(Notification info, DomEventListener clickEvent) {
        getStyle()
                .set("width", "100%")
                .set("--shadow-elevation-2dp_-_box-shadow", "var(--app-layout-notification-shadow)")
                .set("background", "var(--app-layout-notification-background-base-color)")
                .set("color", "var(--app-layout-notification-font-color)");


        Label title = new Label(info.getTitle());
        title.getElement().getStyle()
                .set("font-size", "15px")
                .set("font-weight", "500");

        Label dot = new Label("·");
        dot.getElement().getStyle()
                .set("margin-left", "5px");

        Label timeAgo = new Label(info.getTimeAgo());
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
        if (!info.isRead()) {
            getElement().getStyle().set("border-left", "3px solid var(--app-layout-bar-background-color)");
        }
        HorizontalLayout headerLine = new HorizontalLayout(title, dot, timeAgo);
        headerLine.setSpacing(false);
        headerLine.setAlignItems(FlexComponent.Alignment.CENTER);
        add(headerLine);
        add(descriptionWrapper);
        getContent().getStyle().set("background", "var(--app-layout-notification-background-color)");
        if (clickEvent != null) {
            getElement().addEventListener("click", clickEvent::handleEvent);
        }
        getElement().getClassList().add(info.getStyle());
        if (clickEvent != null) {
            add(new PaperRipple());
        }
    }
}
