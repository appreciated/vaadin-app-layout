package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.github.appreciated.app.layout.builder.window.MaterialNotificationWindow;
import com.github.appreciated.app.layout.builder.window.NotificationWindow;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BUTTON_BADGE;
import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_ELEMENT;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class NavigationNotificationButton extends AbsoluteLayout implements NotificationHolder.NotificationListener {

    private final NavigationButton button;
    private final Label badge;
    Provider<NotificationWindow, NotificationHolder> provider;
    private DefaultNotificationHolder notificationHolder;
    private UI ui;
    private NotificationWindow window;

    public NavigationNotificationButton(String name, Resource icon, DefaultNotificationHolder notificationHolder) {
        this(name, icon, notificationHolder, info -> new MaterialNotificationWindow(info));
    }

    public NavigationNotificationButton(String name, Resource icon, DefaultNotificationHolder notificationHolder, Provider<NotificationWindow, NotificationHolder> provider) {
        this.notificationHolder = notificationHolder;
        this.provider = provider;
        addStyleName(APP_LAYOUT_MENU_ELEMENT);
        setHeight(48, Unit.PIXELS);
        setWidth(100, Unit.PERCENTAGE);
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        button.addClickListener(clickEvent -> buttonClick(clickEvent));
        badge = new Label();
        if (notificationHolder != null) {
            notificationHolder.addStatusListener(this);
        }
        setStatus(notificationHolder);
        badge.addStyleName(APP_LAYOUT_MENU_BUTTON_BADGE);
        addComponent(button);
        addComponent(badge, "right: 0px;");
    }

    public NavigationButton getButton() {
        return button;
    }

    private void setStatus(NotificationHolder status) {
        if (status != null) {
            int unreadNotifications = status.getUnreadNotifications();
            if (unreadNotifications > 0) {
                badge.setVisible(true);
                if (unreadNotifications < 10) {
                    badge.setValue(String.valueOf(unreadNotifications));
                } else {
                    badge.setValue("9+");
                }
            } else {
                badge.setVisible(false);
            }
        } else {
            badge.setVisible(false);
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
                window = provider.get(notificationHolder);
                window.show(clickEvent, false);
                window.addCloseListener(closeEvent -> {
                    window = null;
                });
            });
        } else {
            window.show(clickEvent);
            window = null;
        }
    }

    @Override
    public void onNotificationChanges(NotificationHolder newStatus) {
        if (window != null) {
            window.addNewNotification(notificationHolder);
        }
        setStatus(newStatus);
    }

    @Override
    public void onUnreadCountChange(NotificationHolder holder) {
        setStatus(holder);
    }
}
