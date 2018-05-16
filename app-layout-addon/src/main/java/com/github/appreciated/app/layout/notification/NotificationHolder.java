package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_BAR_NOTIFICATION;

/**
 * This Class is a controller for multiple {@link com.github.appreciated.app.layout.builder.entities.NotificationHolder.Notification} instances
 *
 */

public class NotificationHolder<T extends Notification> {

    private PairComponentFactory<NotificationHolder, T> componentProvider;

    private ArrayList<T> notifications = new ArrayList<>();
    private ArrayList<NotificationListener> onChangeListeners = new ArrayList<>();
    private ArrayList<NotificationClickListener<T>> clickListeners = new ArrayList<>();

    public NotificationHolder() {
    }

    public NotificationHolder(T... notifications) {
        Arrays.stream(notifications).forEach(notification -> this.notifications.add(notification));
    }

    public NotificationHolder(Collection<T> notifications) {
        this.notifications.addAll(notifications);
    }

    public void setComponentProvider(PairComponentFactory<NotificationHolder, T> componentProvider) {
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
        return components.stream().sorted((o1, o2) -> o1.compareTo(o2)).map(o -> getComponent((T) o)).collect(Collectors.toList());
    }

    public void addNotification(T notification) {
        notifications.add(notification);
        notifyListeners();
    }

    public void removeNotification(T notification) {
        notifications.remove(notification);
        notifyListeners();
    }

    public void clearNotifications() {
        notifications.clear();
        notifyListeners();
    }

    public void addStatusListener(NotificationListener listener) {
        onChangeListeners.add(listener);
    }

    public Component getComponent(T message) {
        Component component = componentProvider.getComponent(this, message);
        component.addStyleName(APP_BAR_NOTIFICATION);
        return component;
    }

    public ArrayList<T> getNotifications() {
        Collections.sort(notifications, (o1, o2) -> o1.compareTo(o2));
        return notifications;
    }

    public void onNotificationClicked(T info) {
        notifyClickListeners(info);
        notifyListeners();
    }

    private void notifyListeners() {
        onChangeListeners.forEach(listener -> listener.onNotificationChanges(this));
    }

    private void notifyClickListeners(T info) {
        clickListeners.forEach(listener -> listener.onNotificationClicked(info));
    }

    public void addNotificationClickedListener(NotificationClickListener<T> listener) {
        this.clickListeners.add(listener);
    }

    public int getUnreadNotifications() {
        return (int) notifications.stream().filter(notification -> notification.isRead()).count();
    }

    public interface NotificationListener {
        void onNotificationChanges(NotificationHolder newStatus);

        void onUnreadCountChange(NotificationHolder holder);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}
