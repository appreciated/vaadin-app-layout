package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.PairComponentProvider;
import com.vaadin.ui.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.appreciated.app.layout.Styles.APP_BAR_NOTIFICATION;

/**
 * This Class is a abstract controller of a Component that can display a Notifications
 */

public class NotificationHolder<T extends NotificationHolder.Notification> {

    private PairComponentProvider<NotificationHolder, T> componentProvider;

    private ArrayList<NotificationListener> listeners = new ArrayList<>();
    private ArrayList<T> notifications = new ArrayList<>();
    private NotificationClickListener<T> listener;

    public NotificationHolder() {
    }

    public NotificationHolder(T... notifications) {
        Arrays.stream(notifications).forEach(notification -> this.notifications.add(notification));
    }

    public NotificationHolder(Collection<T> notifications) {
        this.notifications.addAll(notifications);
    }

    public void setComponentProvider(PairComponentProvider<NotificationHolder, T> componentProvider) {
        this.componentProvider = componentProvider;
    }

    public int getNotificationSize() {
        return notifications.size();
    }

    public List<Component> getNotifications(boolean showAll) {
        List<T> components = getNotifications();
        if (!showAll) {
            components = components.size() > 4 ? components.subList(0, 4) : components;
        }
        return components.stream().sorted((o1, o2) -> o1.compare(o1, o2)).map(o -> getComponent((T) o)).collect(Collectors.toList());
    }

    public void addNotification(T notification) {
        notifications.add(notification);
        listeners.forEach(listener -> listener.onNotificationChanges(this));
    }

    public void removeNotification(T notification) {
        notifications.remove(notification);
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

    public void onNotificationClicked(T info) {
        listeners.forEach(listener -> listener.onUnreadCountChange(this));
        if (listener != null) {
            listener.onNotificationClicked(info);
        }
    }

    public void setNotificationClickedListener(NotificationClickListener<T> listener) {
        this.listener = listener;
    }

    public int getUnreadNotifications() {
        return (int) notifications.stream().filter(notification -> notification.isUnnread()).count();
    }

    public interface Notification extends Comparator<Notification> {
        boolean isUnnread();

        DefaultNotification.Priority getPriority();

        LocalDateTime getTime();

        default int compare(Notification o1, Notification o2) {
            if (o1.getPriority() != o2.getPriority()) {
                return o1.getPriority().getValue().compareTo(o2.getPriority().getValue());
            } else {
                return o1.getTime().compareTo(o2.getTime());
            }
        }
    }

    public interface NotificationListener {
        void onNotificationChanges(NotificationHolder newStatus);

        void onUnreadCountChange(NotificationHolder holder);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}
