package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.interfaces.PairComponentFactory;
import com.vaadin.flow.component.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_BAR_NOTIFICATION;

/**
 * This Class is a controller for multiple {@link com.github.appreciated.app.layout.builder.entities.NotificationHolder.Notification} instances
 */

public class NotificationHolder<T extends NotificationHolder.Notification> {

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

    public Component[] getNotificationViews(boolean showAll) {
        List<T> components = getNotificationViews();
        if (!showAll) {
            components = components.size() > 4 ? components.subList(0, 4) : components;
        }
        List<Component> list = components.stream().sorted((o1, o2) -> o1.compare(o1, o2))
                .map(this::getComponent).collect(Collectors.toList());
        return list.toArray(new Component[]{});
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
        component.getElement().getClassList().add(APP_BAR_NOTIFICATION);
        return component;
    }

    public ArrayList<T> getNotificationViews() {
        Collections.sort(notifications, (o1, o2) -> o1.compare(o2, o1));
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
        return (int) notifications.stream().filter(notification -> notification.isUnread()).count();
    }

    public interface Notification extends Comparator<Notification> {
        boolean isUnread();

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
