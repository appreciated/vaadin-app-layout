package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.window.MaterialNotificationWindow;
import com.github.appreciated.app.layout.builder.window.NotificationWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import static com.vaadin.icons.VaadinIcons.BELL;

public class NotificationAppBarButton extends AppBarBadgeButton {

    private UI ui;
    private NotificationWindow window;

    Provider<NotificationWindow, NotificationHolder> provider;

    public NotificationAppBarButton(NotificationHolder holder) {
        this(holder, info -> new MaterialNotificationWindow(info));
    }

    public NotificationAppBarButton(NotificationHolder holder, Provider<NotificationWindow, NotificationHolder> windowProvider) {
        super(BELL, holder);
        provider = windowProvider;
        getButton().addClickListener((Button.ClickListener) this::buttonClick);
    }

    @Override
    public void attach() {
        super.attach();
        ui = getUI();
    }

    private void buttonClick(Button.ClickEvent clickEvent) {
        if (window == null) {
            ui.access(() -> {
                window = provider.get(getNotificationHolder());
                window.show(clickEvent);
                window.addCloseListener(closeEvent -> window = null);
            });
        } else {
            window.show(clickEvent);
            window = null;
        }
    }

    public void refreshNotifications(NotificationHolder notificationHolder) {
        super.refreshNotifications(notificationHolder);
        if (window != null) {
            window.addNewNotification(notificationHolder);
        }
    }
}
