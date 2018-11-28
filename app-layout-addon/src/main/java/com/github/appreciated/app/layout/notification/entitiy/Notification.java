package com.github.appreciated.app.layout.notification.entitiy;

import com.github.appreciated.app.layout.component.appbar.IconBadgeButton;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;

import java.time.LocalDateTime;

/**
 * A notification is a entity which holds data about certain event which can be raised by the application.
 * A Notification can be displayed in the application menu bar via {@link DefaultNotificationHolder} and {@link IconBadgeButton}
 */
public interface Notification extends Comparable<Notification> {

    String getTitle();

    String getDescription();

    Priority getPriority();

    boolean isRead();

    void setRead(boolean isRead);

    boolean isSticky();

    void setSticky(boolean sticky);

    boolean isDismissable();

    void setDismissable(boolean sticky);

    String getImage();

    LocalDateTime getCreationTime();

    void setCreationTime(LocalDateTime creationTime);

    String getTimeAgo();

    String getStyle();

}
