package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.Provider;
import com.github.appreciated.app.layout.component.window.MaterialNotificationWindow;
import com.github.appreciated.app.layout.component.window.NotificationWindow;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import static com.vaadin.icons.VaadinIcons.BELL;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class AppBarNotificationButton extends AppBarBadgeButton {

    Provider<NotificationWindow, NotificationHolder> provider;
    private UI ui;
    private NotificationWindow window;

    public AppBarNotificationButton(NotificationHolder holder) {
        this(holder, info -> new MaterialNotificationWindow(info));
    }

    public AppBarNotificationButton(NotificationHolder holder, boolean closeOnClick) {
        this(holder, closeOnClick, info -> new MaterialNotificationWindow(info));
    }

    public AppBarNotificationButton(NotificationHolder holder, Provider<NotificationWindow, NotificationHolder> windowProvider) {
        this(holder, false, windowProvider);
    }

    public AppBarNotificationButton(NotificationHolder holder, boolean closeOnClick, Provider<NotificationWindow, NotificationHolder> windowProvider) {
        super(BELL, holder);
        provider = windowProvider;
        getButton().addClickListener((Button.ClickListener) this::buttonClick);
        if (closeOnClick) {
            holder.addStatusListener(new NotificationHolder.NotificationListener() {
                @Override
                public void onNotificationChanges(NotificationHolder newStatus) {
                }

                @Override
                public void onUnreadCountChange(NotificationHolder holder) {
                    getUI().access(window::close);
                }
            });
        }
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
