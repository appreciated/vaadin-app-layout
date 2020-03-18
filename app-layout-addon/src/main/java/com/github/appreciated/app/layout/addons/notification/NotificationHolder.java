package com.github.appreciated.app.layout.addons.notification;

import com.github.appreciated.app.layout.addons.NotificationComponent;
import com.github.appreciated.app.layout.addons.notification.interfaces.Notification;
import com.github.appreciated.app.layout.addons.notification.interfaces.NotificationsChangeListener;
import com.github.appreciated.app.layout.component.builder.interfaces.PairComponentFactory;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This Class is a controller for multiple {@link Notification} instances
 */

public abstract class NotificationHolder<T extends Notification> implements Serializable {
    private static final long serialVersionUID = 1L;

    private PairComponentFactory<NotificationHolder<T>, T> componentProvider;
    private PairComponentFactory<NotificationHolder<T>, T> cardComponentProvider;

    private List<T> notifications = new ArrayList<>();
    private List<NotificationsChangeListener<T>> notificationsChangeListeners = new ArrayList<>();
    private List<NotificationClickListener<T>> clickListeners = new ArrayList<>();
    private Notification recentNotification;
    private List<HasText> badgeHolderComponents = new ArrayList<>();
    private Comparator<T> comparator = Comparator.reverseOrder();
    private ArrayList<NotificationComponent> notificationComponents = new ArrayList<>();

    public NotificationHolder(NotificationClickListener<T> listener, T... notifications) {
        this(listener);
        this.notifications.addAll(Arrays.asList(notifications));
    }

    public NotificationHolder(NotificationClickListener<T> listener) {
        if (listener != null) {
            addClickListener(listener);
        }
        setComponentProvider(getComponentProvider());
        setCardComponentProvider(getCardComponentProvider());
    }

    public void addClickListener(NotificationClickListener<T> listener) {
        this.clickListeners.add(listener);
    }

    abstract PairComponentFactory<NotificationHolder<T>, T> getComponentProvider();

    abstract PairComponentFactory<NotificationHolder<T>, T> getCardComponentProvider();

    public void setCardComponentProvider(PairComponentFactory<NotificationHolder<T>, T> componentProvider) {
        this.cardComponentProvider = componentProvider;
    }

    public void setComponentProvider(PairComponentFactory<NotificationHolder<T>, T> componentProvider) {
        this.componentProvider = componentProvider;
    }

    public NotificationHolder(T... notifications) {
        this((NotificationClickListener<T>) null);
        this.add(notifications);
    }

    /**
     * Needs to be called from UI Thread otherwise there will be issues.
     *
     * @param notifications
     */
    public void add(T... notifications) {
        if (UI.getCurrent() == null) {
            throw new IllegalStateException("It seems like NotificationHolder::add wasn't called from the UI Thread. This should be done by using \"UI.getCurrent().access(() -> {})\"");
        }
        Arrays.stream(notifications).forEach(notification -> {
            recentNotification = notification;
            this.notifications.add(notification);
            notifyAddListeners(notification);
            if (notificationComponents.stream().noneMatch(NotificationComponent::isDisplayingNotifications)) {
                com.vaadin.flow.component.notification.Notification notificationView = new com.vaadin.flow.component.notification.Notification(getComponent(notification));
                notificationView.setPosition(com.vaadin.flow.component.notification.Notification.Position.TOP_END);
                notificationView.setDuration(2000);
                notificationView.open();
            }
        });
        notifyListeners();
        updateBadgeCaptions();
    }

    private void notifyAddListeners(T notification) {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationAdded(notification));
    }

    public Component getComponent(T message) {
        return componentProvider.getComponent(this, message);
    }

    private void notifyListeners() {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationChanges(this));
    }

    public void updateBadgeCaptions() {
        if (UI.getCurrent() == null) {
            throw new IllegalStateException("It seems like NotificationHolder::updateBadgeCaptions wasn't called from the UI Thread. This should be done by using \"UI.getCurrent().access(() -> {})\"");
        }
        badgeHolderComponents.forEach(this::updateBadgeCaption);
    }

    private void updateBadgeCaption(HasText hasText) {
        if (hasText != null) {
            int unread = getUnreadNotifications();
            String value;
            if (unread < 1) {
                value = String.valueOf(0);
            } else if (unread < 10) {
                value = String.valueOf(unread);
            } else {
                value = "9+";
            }
            hasText.setText(value);
            if (hasText instanceof Component) {
                ((Component) hasText).setVisible(unread > 0);
            }
        }
    }

    public int getUnreadNotifications() {
        return (int) notifications.stream().filter(notification -> !notification.isRead()).count();
    }

    public NotificationHolder(Collection<T> notifications) {
        this((NotificationClickListener<T>) null);
        this.notifications.addAll(notifications);
    }

    public NotificationHolder(NotificationClickListener<T> listener, Collection<T> notifications) {
        this(listener);
        this.notifications.addAll(notifications);
    }

    public int getNotificationSize() {
        return notifications.size();
    }

    public List<Component> getNotificationCards() {
        List<T> components = getNotifications();
        return components.stream().sorted(comparator).map(this::getCardComponent).collect(Collectors.toList());
    }

    public List<T> getNotifications() {
        notifications.sort(comparator);
        return notifications;
    }

    public Component getCardComponent(T message) {
        return cardComponentProvider.getComponent(this, message);
    }

    public void clearNotifications() {
        if (UI.getCurrent() == null) {
            throw new IllegalStateException("It seems like NotificationHolder::clearNotifications wasn't called from the UI Thread. This should be done by using \"UI.getCurrent().access(() -> {})\"");
        }
        notifications.clear();
        notifyListeners();
        updateBadgeCaptions();
    }

    public void addNotificationsChangeListener(NotificationsChangeListener<T> listener) {
        notificationsChangeListeners.add(listener);
    }

    public void onNotificationClicked(T info) {
        notifyClickListeners(info);
        notifyListeners();
        updateBadgeCaptions();
    }

    private void notifyClickListeners(T info) {
        info.setRead(true);
        clickListeners.forEach(listener -> listener.onNotificationClicked(info));
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void removeClickListener(NotificationClickListener<T> listener) {
        this.clickListeners.remove(listener);
    }

    public Notification getMostRecentNotification() {
        return recentNotification;
    }

    public void bind(HasText text) {
        addBadgeHolderComponent(text);
        updateBadgeCaptions();
    }

    private void addBadgeHolderComponent(HasText text) {
        this.badgeHolderComponents.add(text);
        updateBadgeCaption(text);
    }

    public void onNotificationDismissed(T info) {
        if (!info.isSticky()) {
            remove(info);
        }
        notifyListeners();
    }

    public void remove(T notification) {
        if (UI.getCurrent() == null) {
            throw new IllegalStateException("It seems like NotificationHolder::remove wasn't called from the UI Thread. This should be done by using \"UI.getCurrent().access(() -> {})\"");
        }
        notifications.remove(notification);
        notifyListeners();
        notifyRemoveListeners(notification);
        updateBadgeCaptions();
    }

    private void notifyRemoveListeners(T notification) {
        notificationsChangeListeners.forEach(listener -> listener.onNotificationRemoved(notification));
    }

    public abstract Function<T, String> getDateTimeFormatter();

    public abstract void setDateTimeFormatter(Function<T, String> formatter);

    public void registerNotificationComponent(NotificationComponent notificationButton) {
        this.notificationComponents.add(notificationButton);
    }

    public interface NotificationClickListener<T> {
        void onNotificationClicked(T notification);
    }

}
