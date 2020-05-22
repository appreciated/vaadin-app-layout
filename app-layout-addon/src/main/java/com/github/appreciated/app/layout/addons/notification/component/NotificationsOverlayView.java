package com.github.appreciated.app.layout.addons.notification.component;

import com.github.appreciated.app.layout.addons.notification.NotificationHolder;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.component.appbar.IconButton;
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

import java.util.ArrayList;

public class NotificationsOverlayView<T extends Notification> extends IronOverlay {
    private static final long serialVersionUID = 1L;

    private final IconButton backButton = new IconButton(VaadinIcon.ARROW_LEFT.create());
    private final IconButton clearButton = new IconButton(VaadinIcon.CHECK.create());
    private Component overlayTitle = new Label("Notifications");
    private VerticalLayout results = new VerticalLayout();
    private HorizontalLayout appBarWrapper = new HorizontalLayout(backButton, overlayTitle, clearButton);
    private VerticalLayout wrapper = new VerticalLayout(appBarWrapper, results);
    private NotificationHolder holder;
    private String noNotificationText = "No Notifications";
    private Label noNotificationsLabel;
    private ArrayList<Component> views = new ArrayList<>();

    public NotificationsOverlayView() {
        getElement().getStyle().set("width", "100%");
        overlayTitle.getElement().getStyle().set("width", "100%");
        setVerticalAlign(VerticalOrientation.TOP);
        backButton.addClickListener(buttonClickEvent -> close());
        clearButton.addClickListener(buttonClickEvent -> holder.clearNotifications());
        appBarWrapper.getStyle()
                .set("background", "var(--app-layout-bar-background-base-color)")
                .set("height", "var(--app-bar-height)")
                .set("box-shadow", "var(--app-layout-bar-shadow)")
                .set("padding", "var(--app-layout-bar-padding)")
                .set("flex-shrink", "0")
                .set("z-index", "1");
        appBarWrapper.setWidthFull();
        appBarWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setSizeFull();
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setMargin(false);
        wrapper.setPadding(false);
        wrapper.setSpacing(false);
        wrapper.getStyle()
                .set("max-width", "100vw")
                .set("height", "100vh");
        results.getStyle()
                .set("overflow-y", "auto")
                .set("max-width", "100%")
                .set("min-width", "40%")
                .set("--lumo-size-m", "var(--lumo-size-xl)")
                .set("--lumo-contrast-10pct", "transparent");
        results.setHeightFull();
        results.setWidth("unset");
        add(wrapper);
    }

    @Override
    public void open() {
        super.open();
    }

    public VerticalLayout getWrapper() {
        return wrapper;
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
            views.addAll(this.holder.getNotificationCards());
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

    public void setNoNotificationsText(String noNotificationText) {
        this.noNotificationText = noNotificationText;
        if (this.noNotificationsLabel != null) {
            noNotificationsLabel.setText(noNotificationText);
        }
    }

    public Component getOverlayTitle() {
        return overlayTitle;
    }

    public void setOverlayTitle(Component overlayTitle) {
        appBarWrapper.replace(this.overlayTitle, overlayTitle);
        this.overlayTitle = overlayTitle;
    }

    public void setHolder(NotificationHolder<T> holder) {
        this.holder = holder;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getClearButton() {
        return clearButton;
    }
}
