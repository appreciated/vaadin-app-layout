package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class NotificationsView {

    private Boolean showAll = Boolean.FALSE;
    private NotificationHolder holder;
    private String noNotificationText = "No Notifications";
    private String showAllText = "Show all";
    private Button showAllButton;
    private Label noNotificationsLabel;
    private ArrayList<Component> views = new ArrayList<>();
    private Command showAllCommand = () -> {
        showAll = Boolean.TRUE;
        initViews();
    };

    public NotificationsView(NotificationHolder holder) {
        this.holder = holder;
        initViews();
    }

    Stream<Component> initViews() {
        views.clear();
        if (holder.getNotificationSize() > 0) {
            views.addAll(Arrays.asList(this.holder.getNotificationViews(showAll)));

            if (!showAll && this.holder.getNotificationSize() > 4) {
                showAllButton = new Button(showAllText);
                showAllButton.setWidth("100%");
                showAllButton.setHeight("28px");
                showAllButton.addClickListener(clickEvent -> showAllCommand.execute());
                views.add(showAllButton);
            }
        } else {
            noNotificationsLabel = new Label(noNotificationText);
            noNotificationsLabel.getStyle().set("color", "var(--app-layout-notification-font-color)")
                    .set("font-size", "var(--app-layout-font-size-menu)");
            HorizontalLayout wrapper = new HorizontalLayout(noNotificationsLabel);
            wrapper.setWidth("100%");
            wrapper.setPadding(true);
            wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            views.add(wrapper);
        }
        return views.stream();
    }

    public boolean isShowAll() {
        return this.showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public void refreshNotificationViews(NotificationHolder component) {
        initViews();
    }

    public void setShowAllCommand(Command showAllCommand) {
        this.showAllCommand = showAllCommand;
    }

    public void setShowAllText(String showAllText) {
        this.showAllText = showAllText;
        if (this.showAllButton != null) {
            showAllButton.setText(showAllText);
        }
    }

    public void setNoNotificationText(String noNotificationText) {
        this.noNotificationText = noNotificationText;
        if (this.noNotificationsLabel != null) {
            noNotificationsLabel.setText(noNotificationText);
        }
    }

}
