package com.github.appreciated.app.layout.notification;

import com.github.appreciated.app.layout.builder.design.Styles;
import com.github.appreciated.app.layout.notification.Notification;
import com.vaadin.server.Resource;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Data structure that contains the information for a {@link Notification}
 */
public class DefaultNotification implements Notification {
    private String title;
    private String description;
    private Resource image;
    private Priority priority;
    private boolean isDismissable;
    private boolean isRead;
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

    public DefaultNotification(String title, String description, Resource image, Priority priority, boolean isDismissable) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.priority = priority;
        this.isDismissable = isDismissable;
        this.time = LocalDateTime.now();
        this.isRead = false;
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

    public boolean isRead() {
        return isRead;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isDismissible() {
        return isDismissable;
    }

    public void setDismissible(boolean dismissible) {
        this.isDismissable = dismissible;
    }

    public String getTimeAgo() {
        return new PrettyTime().format(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()));
    }

	@Override
	public int compareTo( Notification otherNotification )
	{
		if(otherNotification == this)
		{
			return 0;
		}
		
        if (this.getPriority() != otherNotification.getPriority()) {
            return this.getPriority().getValue().compareTo(otherNotification.getPriority().getValue());
        } else {
            return this.getTime().compareTo(otherNotification.getTime());
        }
	}

}
