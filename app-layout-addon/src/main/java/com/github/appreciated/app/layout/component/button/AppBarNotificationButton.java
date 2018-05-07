package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.component.window.MaterialNotificationWindow;
import com.github.appreciated.app.layout.component.window.NotificationWindow;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class AppBarNotificationButton extends AppBarBadgeButton {

    Factory<NotificationWindow, NotificationHolder> factory;
    private UI ui;
    private NotificationWindow window;

    public AppBarNotificationButton(NotificationHolder holder) {
        this(holder, info -> new MaterialNotificationWindow(info));
    }

    public AppBarNotificationButton(NotificationHolder holder, boolean closeOnClick) {
        this(holder, closeOnClick, info -> new MaterialNotificationWindow(info));
    }

    public AppBarNotificationButton(NotificationHolder holder, Factory<NotificationWindow, NotificationHolder> windowFactory) {
        this(holder, false, windowFactory);
    }

    public AppBarNotificationButton(NotificationHolder holder, boolean closeOnClick, Factory<NotificationWindow, NotificationHolder> windowFactory) {
        super("test", holder);
        factory = windowFactory;
        getButton().addClickListener(buttonClickEvent -> buttonClick(buttonClickEvent));
        if (closeOnClick) {
            holder.addStatusListener(new NotificationHolder.NotificationListener() {
                @Override
                public void onNotificationChanges(NotificationHolder newStatus) {
                }

                @Override
                public void onUnreadCountChange(NotificationHolder holder) {
                    if (window != null) {
                        window.close();
                    }
                }
            });
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ui = UI.getCurrent();
    }

    private void buttonClick(ClickEvent<Button> clickEvent) {
        if (window == null) {
            ui.access(() -> {
                window = factory.get(getNotificationHolder());
                window.show(clickEvent);
                window.addDialogCloseActionListener(closeEvent -> window = null);
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
