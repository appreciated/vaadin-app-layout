package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.window.NotificationWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import static com.vaadin.icons.VaadinIcons.BELL;

public class NotificationAppBarButton extends AppBarBadgeButton {

    private final NotificationWindow window;
    private UI ui;

    public NotificationAppBarButton(NotificationHolder holder) {
        super(BELL, holder);
        window = new NotificationWindow(holder);
        getButton().addClickListener((Button.ClickListener) this::buttonClick);
    }

    @Override
    public void attach() {
        super.attach();
        ui = getUI();
    }

    private void buttonClick(Button.ClickEvent clickEvent) {
        ui.access(() -> {
            window.toggleWindow(clickEvent);
        });
    }

    public void refreshNotifications(NotificationHolder notificationHolder, Component component) {
        getUI().access(() -> {
            super.refreshNotifications(notificationHolder, component);
            window.addNewNotification(component);
        });
    }
}
