package com.github.appreciated.applayout.notification;

import com.github.appreciated.applayout.builder.interfaces.PairComponentFactory;
import com.github.appreciated.applayout.notification.entitiy.Notification;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This Class is a controller for multiple {@link Notification} instances
 */

public class NotificationHolder<T extends Notification> {

    private PairComponentFactory<NotificationHolder, T> componentProvider;

    private ArrayList<T> notifications = new ArrayList<>();
    private ArrayList<NotificationsChangeListener> notificationsChangeListeners = new ArrayList<>();
    private ArrayList<NotificationClickListener<T>> clickListeners = new ArrayList<>();
    private Notification recentNotification;

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
        return components.stream().sorted(Comparable::compareTo).map(o -> getComponent(o)).collect(Collectors.toList());
    }

    public void addNotification(T notification) {
        recentNotification = notification;
        notifications.add(notification);
        notifyListeners();
        notifyAddListeners(notification);
    }

    public void removeNotification(T notification) {
        notifications.remove(notification);
        notifyListeners();
        notifyRemoveListeners(notification);
    }

    public void clearNotifications() {
        notifications.clear();
        notifyListeners();
    }

    public void addNotificationsChangeListener(NotificationsChangeListener listener) {
        notificationsChangeListeners.add(listener);
    }

    public Component getComponent(T message) {
        Component component = componentProvider.getComponent(this, message);
        return component;
    }

    public Component[] getNotificationViews(boolean showAll) {
        List<T> components = getNotificationViews();
        if (!showAll) {
            components = components.size() > 4 ? components.subList(0, 4) : components;
        }
        List<Component> list = components.stream().sorted(Comparable::compareTo)
                .map(this::getComponent).collect(Collectors.toList());
        return list.toArray(new Component[]{});
    }

    public ArrayList<T> getNotificationViews() {
        Collections.sort(notifications, Comparable::compareTo);
        return notifications;
    }

    public ArrayList<T> getNotifications() {
        Collections.sort(notifications, Comparable::compareTo);
        return notifications;
    }

    public void onNotificationClicked(T info) {
        notifyClickListeners(info);
        notifyListeners();
    }

    private void notifyListeners() {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationChanges(this));
    }

    private void notifyAddListeners(Notification notification) {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationAdded(notification));
    }

    private void notifyRemoveListeners(Notification notification) {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationRemoved(notification));
    }

    private void notifyClickListeners(T info) {
        clickListeners.forEach(listener -> listener.onNotificationClicked(info));
    }

    public void addClickListener(NotificationClickListener<T> listener) {
        this.clickListeners.add(listener);
    }

    public void removeClickListener(NotificationClickListener<T> listener) {
        this.clickListeners.remove(listener);
    }

    public int getUnreadNotifications() {
        return (int) notifications.stream().filter(notification -> !notification.isRead()).count();
    }

    public Notification getRecentNotification() {
        return recentNotification;
    }

    public void bind(HasText text) {
        setBadgeCaption(text);
        addNotificationsChangeListener(new NotificationsChangeListener() {
            @Override
            public void onNotificationChanges(NotificationHolder holder) {

            }

            @Override
            public void onNotificationAdded(Notification notification) {
                setBadgeCaption(text);
            }

            @Override
            public void onNotificationRemoved(Notification notification) {
                setBadgeCaption(text);
            }
        });
    }

    private void setBadgeCaption(HasText text) {
        int unread = NotificationHolder.this.getUnreadNotifications();
        if (unread < 1) {
            text.setText(String.valueOf(0));
        } else if (unread < 10) {
            text.setText(String.valueOf(unread));
        } else {
            text.setText("9+");
        }
        if (text instanceof Component) {
            ((Component) text).setVisible(unread > 0);
        }
    }

    public interface NotificationsChangeListener {
        void onNotificationChanges(NotificationHolder holder);

        void onNotificationAdded(Notification notification);

        void onNotificationRemoved(Notification notification);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}