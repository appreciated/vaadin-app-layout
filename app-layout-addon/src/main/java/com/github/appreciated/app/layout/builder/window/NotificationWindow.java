package com.github.appreciated.app.layout.builder.window;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


public class NotificationWindow extends Window {

    private VerticalLayout notificationsView;

    public NotificationWindow(NotificationHolder holder) {
        super();
        setWidth(300, Sizeable.Unit.PIXELS);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
        setCloseShortcut(ShortcutAction.KeyCode.ESCAPE, null);
        setContent(getNotificationLayout(holder));
    }

    VerticalLayout getNotificationLayout(NotificationHolder notifications) {
        VerticalLayout notificationsLayout = new VerticalLayout();
        notificationsLayout.addStyleName(Styles.APP_BAR_NOTIFICATION_WINDOW);
        notificationsLayout.setSpacing(false);
        notificationsLayout.setMargin(false);
        Label title = new Label("Notifications");
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        notificationsLayout.addComponent(new VerticalLayout(title));
        notificationsView = new VerticalLayout(notifications.getCurrentComponents());
        notificationsView.addStyleName(Styles.APP_BAR_NOTIFICATION_LIST);
        notificationsView.setMargin(false);
        notificationsView.setSpacing(false);
        notificationsLayout.addComponent(notificationsView);
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
        return notificationsLayout;
    }


    public void toggleWindow(Button.ClickEvent clickEvent) {
        if (!isAttached())

        {
            if (UI.getCurrent().getPage().getBrowserWindowWidth() < clickEvent.getClientX() + 150) {
                setPositionX(UI.getCurrent().getPage().getBrowserWindowWidth() - 300);
            } else {
                setPositionX(clickEvent.getClientX() - 150);
            }
            setPositionY(clickEvent.getClientY() - clickEvent.getRelativeY() + 67);
            UI.getCurrent().addWindow(this);
            focus();
        } else

        {
            getUI().removeWindow(this);
        }
    }

    public void addNewNotification(Component component) {
        if (component != null) {
            while (notificationsView.getComponentCount() > 4) {
                notificationsView.removeComponent(notificationsView.getComponent(notificationsView.getComponentCount() - 1));
            }
            notificationsView.addComponentAsFirst(component);
        }
    }
}
