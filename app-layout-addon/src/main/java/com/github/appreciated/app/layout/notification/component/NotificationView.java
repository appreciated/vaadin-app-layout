package com.github.appreciated.app.layout.notification.component;

import com.github.appreciated.app.layout.component.appbar.IconButton;
import com.github.appreciated.app.layout.component.menu.RoundImage;
import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.notification.entitiy.Notification;
import com.github.appreciated.app.layout.notification.listener.NotificationListener;
import com.github.appreciated.card.RippleClickableCard;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.DomListenerRegistration;

public class NotificationView<T extends Notification> extends RippleClickableCard {

    private final VerticalLayout wrapper;
    private final T info;
    private final NotificationHolder<T> holder;
    private IconButton dismissButton;
    private DomListenerRegistration registration;

    public NotificationView(T info, NotificationHolder<T> holder) {
        this(info, holder, null);
    }

    public NotificationView(T info, NotificationHolder<T> holder, NotificationListener listener) {
        this.info = info;
        this.holder = holder;
        setWidth("100%");
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
        getContent().setPadding(true);
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
    }

    public void setHighlightBorder(boolean highlight) {
        getContent().getStyle().set("border-left", "3px solid " + (highlight ? info.getPriority().getStyle() : "transparent"));
    }

    public void setNotificationListener(NotificationListener listener) {
        if (listener != null && registration == null) {
            registration = getElement().addEventListener("click", domEvent -> listener.onClick());
        }
    }
}
