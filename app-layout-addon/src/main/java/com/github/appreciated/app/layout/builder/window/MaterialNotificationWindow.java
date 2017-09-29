package com.github.appreciated.app.layout.builder.window;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class MaterialNotificationWindow extends NotificationWindow {

    private VerticalLayout notificationsView;

    public MaterialNotificationWindow(NotificationHolder holder) {
        super(holder);
        addStyleName("translucent");
        //setBlurListenerEnabled(false);
    }

    VerticalLayout getNotificationLayout(NotificationHolder notifications) {
        List<Component> components = notifications.getNotifications(isShowAll());
        notificationsView = new VerticalLayout(components.toArray(new Component[]{}));
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_WINDOW);
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);
        notificationsView.setSpacing(true);
        /*
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidth("100%");
        footer.setSpacing(false);
        Button showAll = new Button("View All Notifications", clickEvent -> {
        });
        showAll.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        showAll.addStyleName(ValoTheme.BUTTON_SMALL);
        footer.addComponent(showAll);
        footer.setComponentAlignment(showAll, Alignment.TOP_CENTER);
        notificationsLayout.addComponent(footer);
        */
        return notificationsView;
    }

    @Override
    public VerticalLayout getNotificationsView() {
        return notificationsView;
    }
}
