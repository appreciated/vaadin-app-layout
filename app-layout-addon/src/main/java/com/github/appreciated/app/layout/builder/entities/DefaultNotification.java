package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.Styles;
import com.github.appreciated.app.layout.builder.entities.NotificationHolder.Notification;
import com.vaadin.server.Resource;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Data structure to contain a notification
 */

public class DefaultNotification implements Notification {
    String title;
    String description;
    Resource image;
    private Priority priority;
    private boolean dismissible;
    private boolean unread;
    private LocalDateTime time;

    public DefaultNotification(String title, String description) {
        this(title, description, (Resource) null);
    }

    public DefaultNotification(String title, String description, Resource image) {
        this(title, description, image, Priority.MEDIUM, true);
    }

    public DefaultNotification(String title, String description, boolean dismissible) {
        this(title, description, null, null, dismissible);
    }

    public DefaultNotification(String title, String description, Priority priority) {
        this(title, description, null, priority, true);
    }

    public DefaultNotification(String title, String description, Priority priority, boolean dismissible) {
        this(title, description, null, priority, dismissible);
    }

    public DefaultNotification(String title, String description, Resource image, Priority priority, boolean dismissible) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.priority = priority;
        this.dismissible = dismissible;
        time = LocalDateTime.now();
        unread = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getStyle() {
        switch (priority) {
            case LOW:
                return Styles.APP_BAR_NOTIFICATION_PRIORITY_LOW;
            case MEDIUM:
                return Styles.APP_BAR_NOTIFICATION_PRIORITY_MEDIUM;
            case HIGH:
                return Styles.APP_BAR_NOTIFICATION_PRIORITY_HIGH;
        }
        return "";
    }

    public boolean isUnnread() {
        return unread;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public boolean isDismissible() {
        return dismissible;
    }

    public void setDismissible(boolean dismissible) {
        this.dismissible = dismissible;
    }

    public String getTimeAgo() {
        return new PrettyTime().format(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public enum Priority {
        HIGH(2), MEDIUM(1), LOW(0);

        private Integer priority;

        Priority(int priority) {
            this.priority = priority;
        }

        public Integer getValue() {
            return priority;
        }
    }

}
