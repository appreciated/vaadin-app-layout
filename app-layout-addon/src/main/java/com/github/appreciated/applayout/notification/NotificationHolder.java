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

public abstract class NotificationHolder<T extends Notification> {

    private PairComponentFactory<NotificationHolder, T> componentProvider;

    private ArrayList<T> notifications = new ArrayList<>();
    private ArrayList<NotificationsChangeListener> notificationsChangeListeners = new ArrayList<>();
    private ArrayList<NotificationClickListener<T>> clickListeners = new ArrayList<>();
    private Notification recentNotification;
    private HasText text;

    public NotificationHolder(NotificationClickListener<T> listener) {
        Objects.requireNonNull(listener);
        addClickListener(listener);
        setComponentProvider(getComponentProvider());
    }

    public NotificationHolder(NotificationClickListener<T> listener, T... notifications) {
        this(listener);
        Arrays.stream(notifications).forEach(notification -> this.notifications.add(notification));
    }

    public NotificationHolder(NotificationClickListener<T> listener, Collection<T> notifications) {
        this(listener);
        notifications.addAll(notifications);
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
        List<T> components = getNotifications();
        if (!showAll) {
            components = components.size() > 4 ? components.subList(0, 4) : components;
        }
        List<Component> list = components.stream().sorted(Comparable::compareTo)
                .map(this::getComponent).collect(Collectors.toList());
        return list.toArray(new Component[]{});
    }

    public List<T> getNotifications() {
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
        info.setRead(true);
        if (!info.isSticky()) {
            removeNotification(info);
        }
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
        setBadgeComponent(text);
        addNotificationsChangeListener(new NotificationsChangeListener() {
            @Override
            public void onNotificationChanges(NotificationHolder holder) {
                updateBadgeCaption();
            }
        });
    }

    private void setBadgeComponent(HasText text) {
        this.text = text;
        updateBadgeCaption();
    }

    private void updateBadgeCaption() {
        if (text != null) {
            int unread = getUnreadNotifications();
            String value;
            if (unread < 1) {
                value = String.valueOf(0);
            } else if (unread < 10) {
                value = String.valueOf(unread);
            } else {
                value = "9+";
            }
            text.setText(value);
            if (text instanceof Component) {
                ((Component) text).setVisible(unread > 0);
            }
        }
    }

    abstract PairComponentFactory<NotificationHolder, T> getComponentProvider();

    public interface NotificationsChangeListener {
        default void onNotificationChanges(NotificationHolder holder) {
        }

        default void onNotificationAdded(Notification notification) {
        }

        default void onNotificationRemoved(Notification notification) {
        }
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T newStatus);
    }

}