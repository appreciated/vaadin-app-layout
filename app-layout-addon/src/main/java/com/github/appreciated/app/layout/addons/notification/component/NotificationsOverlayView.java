package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.card.Card;
import com.github.appreciated.ironoverlay.IronOverlay;
import com.github.appreciated.ironoverlay.VerticalOrientation;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.Command;

import java.util.ArrayList;

public class NotificationsOverlayView<T extends Notification> extends IronOverlay {

    private VerticalLayout results = new VerticalLayout();

    private Button closeButton = new Button();
    private HorizontalLayout wrapper = new HorizontalLayout(results);
    private VerticalLayout closeWrapper = new VerticalLayout(closeButton, wrapper);
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

    public NotificationsOverlayView() {
        getElement().getStyle().set("width", "100%");
        setVerticalAlign(VerticalOrientation.TOP);
        closeWrapper.getStyle()
                .set("height", "var(--app-bar-height)");
        closeButton.setIcon(VaadinIcon.CLOSE.create());
        closeButton.addClickListener(buttonClickEvent -> close());
        closeWrapper.setAlignItems(FlexComponent.Alignment.END);
        closeWrapper.setHeight("100vh");
        closeWrapper.setMargin(false);
        closeWrapper.setPadding(false);
        closeButton.getStyle()
                .set("background", "var(--app-layout-bar-background-base-color)")
                .set("height", "64px")
                .set("width", "64px")
                .set("border-radius", "100px")
                .set("margin-right", "14px")
                .set("margin-top", "26px");
        wrapper.setSizeFull();
        wrapper.setMargin(false);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        wrapper.getStyle().set("max-width", "100vw");
        results.setSizeFull();
        results.getStyle()
                .set("flex", "0 1 40%")
                .set("--lumo-size-m", "var(--lumo-size-xl)")
                .set("--lumo-contrast-10pct", "transparent");
        add(closeWrapper);
    }

    @Override
    public void open() {
        super.open();
    }

    public HorizontalLayout getWrapper() {
        return wrapper;
    }

    public boolean isShowAll() {
        return this.showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }

    public void refreshNotificationViews() {
        getResults().removeAll();
        initViews();
        views.forEach(component -> getResults().add(component));
    }

    public VerticalLayout getResults() {
        return results;
    }

    private void initViews() {
        views.clear();
        if (holder.getNotificationSize() > 0) {
            views.addAll(this.holder.getNotificationCards(showAll));

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

            VerticalLayout labelWrapper = new VerticalLayout(noNotificationsLabel);
            labelWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
            labelWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            Card wrapper = new Card(labelWrapper);
            wrapper.setWidthFull();
            wrapper.setBackground("var(--lumo-base-color)");
            wrapper.setWidth("100%");
            wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            views.add(wrapper);
        }
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

    public void setHolder(NotificationHolder<T> holder) {
        this.holder = holder;
    }
}
