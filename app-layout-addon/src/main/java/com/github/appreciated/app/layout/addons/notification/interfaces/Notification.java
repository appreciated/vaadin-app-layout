package com.github.appreciated.app.layout.addons.notification.interfaces;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.entity.Priority;
import com.github.appreciated.app.layout.component.appbar.ComponentBadgeWrapper;

import java.time.LocalDateTime;

/**
 * A notification is a entity which holds data about certain event which can be raised by the application.
 * A Notification can be displayed in the application menu bar via {@link DefaultNotificationHolder} and {@link ComponentBadgeWrapper}
 */
public interface Notification extends Comparable<Notification> {

    String getTitle();

    String getDescription();

    boolean isDismissable();

    void setDismissable(boolean sticky);

    String getImage();

    @Override
    default int compareTo(Notification o) {
        if (o == this) {
            return 0;
        }
        if (this.getPriority().getValue() > 2 || o.getPriority().getValue() > 2) {
            return getPriority().getValue().compareTo(o.getPriority().getValue());
        } else if (this.isSticky() != o.isSticky()) {
            return !isSticky() ? -1 : 1;
        } else if (getPriority() != o.getPriority()) {
            return getPriority().getValue().compareTo(o.getPriority().getValue());
        } else if (isRead() != o.isRead()) {
            return !isRead() ? -1 : 1;
        } else {
            return this.getCreationTime().compareTo(o.getCreationTime());
        }
    }

    Priority getPriority();

    boolean isSticky();

    boolean isRead();

    void setRead(boolean isRead);

    LocalDateTime getCreationTime();

    void setCreationTime(LocalDateTime creationTime);

    void setSticky(boolean sticky);

}
