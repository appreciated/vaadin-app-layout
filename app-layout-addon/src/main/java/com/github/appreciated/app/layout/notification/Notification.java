package com.github.appreciated.app.layout.notification;

import java.time.LocalDateTime;

/**
 * A notification is a entity which holds data about certain event which can be raised by the application.
 * A Notification can be displayed in the application menu bar via {@link DefaultNotificationHolder} and {@link AppBarNotificationButton}
 *
 */
public interface Notification extends Comparable<Notification> {

    public String getTitle();
    
    public String getDescription();
    
    public Priority getPriority();
    
    boolean isRead();
    
    public void setRead(boolean isRead);

    public String getImage();
    
    public LocalDateTime getCreationTime();
    
    public void setCreationTime(LocalDateTime creationTime);
    
    public String getTimeAgo();
    
    public String getStyle();
    
}
