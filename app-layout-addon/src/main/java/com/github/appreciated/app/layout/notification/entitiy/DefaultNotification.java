package com.github.appreciated.app.layout.notification.entitiy;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An implementation of {@link Notification}.
 */
public class DefaultNotification implements Notification {

    private String title;
    private String description;
    private String image;
    private Priority priority = Priority.MEDIUM;
    private boolean isSticky = false;
    private boolean isRead = false;
    private LocalDateTime creationTime = LocalDateTime.now();
    private boolean dismissable = true;

    /**
     * Creates a new {@link DefaultNotification} with the given arguments.
     *
     * @param title       The title for this notification. Must not be <code>null</code>.
     * @param description The description for this notification. Must not be <code>null</code>.
     * @throws NullPointerException in case of any argument is <code>null</code>.
     */
    public DefaultNotification(String title, String description) {
        this(title, description, null);
    }

    public DefaultNotification(String title, String description, Priority priority) {
        Objects.requireNonNull(title, "The title must not be null.");
        Objects.requireNonNull(description, "The description must not be null.");
        this.title = title;
        this.description = description;
        if (priority != null) {
            this.priority = priority;
        }
    }

    public DefaultNotification(String title, String description, Priority priority, boolean isSticky) {
        this(title, description, priority);
        this.isSticky = isSticky;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title, "The title must not be null.");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Objects.requireNonNull(description, "The description must not be null.");
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStyle() {

        if (priority == null) {
            return "";
        }

        return priority.getStyle();
    }

    @Override
    public boolean isDismissable() {
        return dismissable;
    }

    @Override
    public void setDismissable(boolean dismissable) {
        this.dismissable = dismissable;
    }

    @Override
    public int compareTo(Notification otherNotification) {
        if (otherNotification == this) {
            return 0;
        }
        if (this.getPriority().getValue() > 2 || otherNotification.getPriority().getValue() > 2) {
            return this.getPriority().getValue().compareTo(otherNotification.getPriority().getValue());
        } else if (this.isSticky() != otherNotification.isSticky()) {
            return !isSticky() ? -1 : 1;
        } else if (this.getPriority() != otherNotification.getPriority()) {
            return this.getPriority().getValue().compareTo(otherNotification.getPriority().getValue());
        } else if (this.isRead() != otherNotification.isRead()) {
            return !isRead() ? -1 : 1;
        } else {
            return this.getCreationTime().compareTo(otherNotification.getCreationTime());
        }
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public boolean isSticky() {
        return isSticky;
    }

    public boolean isRead() {
        return isRead;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(LocalDateTime creationTime) {
        Objects.requireNonNull(creationTime, "The creationTime must not be null.");
        this.creationTime = creationTime;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setSticky(boolean isSticky) {
        this.isSticky = isSticky;
    }
}
