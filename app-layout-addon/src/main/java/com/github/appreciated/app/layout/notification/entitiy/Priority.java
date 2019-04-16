package com.github.appreciated.app.layout.notification.entitiy;

import com.github.appreciated.app.layout.design.Styles;

public enum Priority {

    ERROR(4, Styles.APP_BAR_NOTIFICATION_PRIORITY_HIGH),
    WARNING(3, Styles.APP_BAR_NOTIFICATION_PRIORITY_HIGH),
    HIGH(2, Styles.APP_BAR_NOTIFICATION_PRIORITY_HIGH),
    MEDIUM(1, Styles.APP_BAR_NOTIFICATION_PRIORITY_MEDIUM),
    LOW(0, Styles.APP_BAR_NOTIFICATION_PRIORITY_LOW);

    private Integer priority;

    private String style;

    Priority(int priority, String style) {
        this.priority = priority;
        this.style = style;
    }

    public Integer getValue() {
        return priority;
    }

    public String getStyle() {
        return this.style;
    }
}