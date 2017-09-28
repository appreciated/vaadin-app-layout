package com.github.appreciated.app.layout.builder.window;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.APP_BAR_NOTIFICATION;


public class NotificationWindow extends Window {

    private VerticalLayout notificationsView;
    boolean hasBlurListener = false;
    boolean blurListenerEnabled = true;

    public NotificationWindow(NotificationHolder holder) {
        super();
        setWidth(300, Sizeable.Unit.PIXELS);
        setClosable(false);
        setResizable(false);
        setDraggable(false);
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

    public VerticalLayout getNotificationsView() {
        return notificationsView;
    }

    public void show(Button.ClickEvent clickEvent) {
        if (!isAttached()) {
            if (UI.getCurrent().getPage().getBrowserWindowWidth() < clickEvent.getClientX() + 150) {
                setPositionX(UI.getCurrent().getPage().getBrowserWindowWidth() - 300);
            } else {
                setPositionX(clickEvent.getClientX() - 150);
            }
            setPositionY(clickEvent.getClientY() - clickEvent.getRelativeY() + 67);
            UI.getCurrent().addWindow(this);
            focus();
            if (!hasBlurListener && blurListenerEnabled) {
                hasBlurListener = true;
                addBlurListener(blurEvent -> {
                    if (this != null) {
                        UI.getCurrent().removeWindow(this);
                    }
                });
            }
        } else {
            UI.getCurrent().removeWindow(this);
        }
    }

    public void addNewNotification(Component component) {
        VerticalLayout view = getNotificationsView();
        component.addStyleName(APP_BAR_NOTIFICATION);
        if (component != null) {
            while (view.getComponentCount() >= 5) {
                view.removeComponent(view.getComponent(view.getComponentCount() - 1));
            }
            view.addComponentAsFirst(component);
        }
    }

    public void setBlurListenerEnabled(boolean blurListener) {
        this.blurListenerEnabled = blurListener;
    }
}
