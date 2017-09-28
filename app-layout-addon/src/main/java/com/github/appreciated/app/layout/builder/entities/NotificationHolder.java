package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.PairComponentProvider;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.github.appreciated.app.layout.Styles.APP_BAR_NOTIFICATION;

public class NotificationHolder<T> {

    private ArrayList<NotificationListener> listeners = new ArrayList<>();
    private ArrayList<T> notifications = new ArrayList<>();
    private PairComponentProvider<NotificationHolder<T>, T> componentProvider;
    private NotificationClickListener<T> listener;

    public NotificationHolder() {
    }

    public NotificationHolder(T... notifications) {
        Arrays.stream(notifications).forEach(notification -> this.notifications.add(notification));
    }

    public NotificationHolder(Collection<T> notifications) {
        this.notifications.addAll(notifications);
    }

    public int getNotificationSize() {
        return notifications.size();
    }

    public void setComponentProvider(PairComponentProvider<NotificationHolder<T>, T> componentProvider) {
        this.componentProvider = componentProvider;
    }

    public void addNotification(T notification) {
        notifications.add(notification);
        listeners.forEach(listener -> listener.onNotificationChanges(this, componentProvider.getComponent(this, notification)));
    }

    public void addStatusListener(NotificationListener listener) {
        listeners.add(listener);
    }

    public Component[] getCurrentComponents() {
        ArrayList<Component> components = new ArrayList<>();
        notifications.forEach(t -> {
            Component c = componentProvider.getComponent(this, t);
            c.addStyleName(APP_BAR_NOTIFICATION);
            components.add(c);
        });
        return components.toArray(new Component[]{});
    }

    public void setNotificationClickedListener(NotificationClickListener<T> listener) {
        this.listener = listener;
    }

    public void onNotificationClicked(T info) {
        if (listener != null) {
            listener.onNotificationClicked(info);
        }
    }

    public interface NotificationListener {
        void onNotificationChanges(NotificationHolder newStatus, Component c);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}
