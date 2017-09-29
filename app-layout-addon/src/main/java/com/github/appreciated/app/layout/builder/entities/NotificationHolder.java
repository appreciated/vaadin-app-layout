package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.PairComponentProvider;
import com.vaadin.ui.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.appreciated.app.layout.Styles.APP_BAR_NOTIFICATION;

public class NotificationHolder<T extends Comparator<T>> {

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
        listeners.forEach(listener -> listener.onNotificationChanges(this));
    }

    public void addStatusListener(NotificationListener listener) {
        listeners.add(listener);
    }

    public Component getComponent(T message) {
        Component component = componentProvider.getComponent(this, message);
        component.addStyleName(APP_BAR_NOTIFICATION);
        return component;
    }

    public ArrayList<T> getNotifications() {
        Collections.sort(notifications, (o1, o2) -> o1.compare(o2, o1));
        return notifications;
    }

    public List<Component> getNotifications(boolean showAll) {
        List<T> components = getNotifications();
        if (!showAll) {
            components = components.size() > 4 ? components.subList(0, 4) : components;
        }
        return components.stream().sorted((o1, o2) -> o1.compare(o1, o2)).map(o -> getComponent(o)).collect(Collectors.toList());
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
        void onNotificationChanges(NotificationHolder newStatus);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}
