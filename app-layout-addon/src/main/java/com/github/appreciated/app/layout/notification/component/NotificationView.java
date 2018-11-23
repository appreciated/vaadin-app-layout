package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.github.appreciated.app.layout.component.appmenu.RoundImage;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.github.appreciated.app.layout.notification.listener.NotificationListener;
import com.github.appreciated.app.layout.webcomponents.papercard.PaperCard;
import com.github.appreciated.app.layout.webcomponents.paperripple.PaperRipple;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.DomListenerRegistration;

public class NotificationView extends PaperCard {

    private final VerticalLayout wrapper;
    private IconButton dismissButton;
    private NotificationListener listener;
    private PaperRipple ripple;
    private DomListenerRegistration registration;

    public NotificationView(Notification info) {
        this(info, null);
    }

    public NotificationView(Notification info, NotificationListener listener) {
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
        setHighlightBorder(!info.isRead());
        HorizontalLayout headerLine = new HorizontalLayout(title, dot, timeAgo);
        headerLine.setSpacing(false);
        headerLine.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper = new VerticalLayout(headerLine, descriptionWrapper);
        wrapper.setMargin(false);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        add(wrapper);

        add(headerLine);
        add(descriptionWrapper);
        getContent().getStyle().set("background", "var(--app-layout-notification-background-color)");
        getElement().getClassList().add(info.getStyle());
        setNotificationListener(listener);

        if (info.isDismissable()) {
            dismissButton = new IconButton(VaadinIcon.CLOSE_BIG.create(), paperIconButtonClickEvent -> {
                if (listener != null) {
                    listener.onDismiss();
                }
            });
            dismissButton.getStyle()
                    .set("position", "absolute")
                    .set("right", "0px")
                    .set("margin", "1px 5px 0 0")
                    .set("top", "0px");
            dismissButton.getButton().getElement().getStyle().set("line-height", "0px");
            dismissButton.setWidth("27px");
            dismissButton.setHeight("27px");
            add(dismissButton);
        }
        addRipple();
    }

    public void setHighlightBorder(boolean highlight) {
        getElement().getStyle().set("border-left", "3px solid " + (highlight ? "var(--app-layout-notification-highlight-color)" : "transparent"));
    }

    public void setNotificationListener(NotificationListener listener) {
        this.listener = listener;
        if (listener != null && registration == null) {
            registration = getElement().addEventListener("click", domEvent -> {
                if (listener != null) {
                    listener.onClick();
                }
            });
        }
        addRipple();
    }

    private void addRipple() {
        if (listener != null && ripple == null) {
            this.ripple = new PaperRipple();
            wrapper.add(this.ripple);
        }
    }
}
