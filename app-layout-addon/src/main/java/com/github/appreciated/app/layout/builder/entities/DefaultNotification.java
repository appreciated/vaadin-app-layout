package com.github.appreciated.app.layout.builder.entities;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.notification.Notification;
import com.github.appreciated.app.layout.notification.Priority;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Data structure that contains the information for a {@link Notification}
 */

public class DefaultNotification implements Notification {
    String title;
    String description;
    String image;
    private Priority priority;
    private boolean dismissible;
    private boolean unread;
    private LocalDateTime creationTime;

    public DefaultNotification(String title, String description) {
        this(title, description, (String) null);
    }

    public DefaultNotification(String title, String description, String image) {
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

    public DefaultNotification(String title, String description, String image, Priority priority, boolean dismissible) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.priority = priority;
        this.dismissible = dismissible;
        creationTime = LocalDateTime.now();
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

    @Override
    public Priority getPriority() {
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setImage(String image) {
        this.image = image;
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


    @Override
    public boolean isRead() {
        return false;
    }

    @Override
    public void setRead(boolean isRead) {
        this.unread = isRead;
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
        return new PrettyTime().format(Date.from(creationTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public int compareTo(Notification o) {
        return 0;
    }

}
