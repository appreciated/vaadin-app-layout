package com.github.appreciated.app.layout.component.button;

import com.github.appreciated.app.layout.notification.NotificationHolder;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;

/**
 * A component which opens a window containing the notifications on click closely to this component. Also showing an
 * indicator how many new notifications are available
 */
public class NavigationNotificationButton extends PaperDrawerIconItem implements NotificationHolder.NotificationListener {
    public NavigationNotificationButton(String title, String icon) {
        super(title, icon);
    }

    @Override
    public void onNotificationChanges(NotificationHolder newStatus) {

    }

    @Override
    public void onUnreadCountChange(NotificationHolder holder) {

    }
/*
    private final NavigationButton button;
    private final Label badge;
    Factory<NotificationWindow, NotificationHolder> factory;
    private DefaultNotificationHolder notificationHolder;
    private Optional<UI> ui = Optional.empty();
    private NotificationWindow window;

    public NavigationNotificationButton(String name, Icon icon, DefaultNotificationHolder notificationHolder) {
        this(name, icon, notificationHolder, info -> new MaterialNotificationWindow(info));
    }

    public NavigationNotificationButton(String name, Icon icon, DefaultNotificationHolder notificationHolder, Factory<NotificationWindow, NotificationHolder> factory) {
        this.notificationHolder = notificationHolder;
        this.factory = factory;
        getElement().getClassList().add(APP_LAYOUT_MENU_ELEMENT);
        setHeight("48px");
        setWidth("100%");
        button = new NavigationButton(name, icon);
        button.setSizeFull();
        button.addClickListener(clickEvent -> buttonClick(clickEvent));
        badge = new Label();
        if (notificationHolder != null) {
            notificationHolder.addStatusListener(this);
        }
        setStatus(notificationHolder);
        badge.getElement().getClassList().add(APP_LAYOUT_MENU_BUTTON_BADGE);
        add(button);
        add(badge);
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
                    badge.setText(String.valueOf(unreadNotifications));
                } else {
                    badge.setText("9+");
                }
            } else {
                badge.setVisible(false);
            }
        } else {
            badge.setVisible(false);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        ui = getUI();
    }

    private void buttonClick(ClickEvent<Button> clickEvent) {
        if (window == null) {
            ui.ifPresent(ui1 -> {
                window = factory.get(notificationHolder);
                window.show(clickEvent, false);
                window.addDialogCloseActionListener(closeEvent -> window = null);
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
    }*/
}
