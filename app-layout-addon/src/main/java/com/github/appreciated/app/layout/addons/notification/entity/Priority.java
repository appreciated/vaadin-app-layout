package com.github.appreciated.app.layout.addons.notification.entity;

public enum Priority {
    ERROR(4, "var(--app-layout-notification-error-color)"),
    WARNING(3, "var(--app-layout-notification-warning-color)"),
    HIGH(2, "var(--app-layout-notification-highlight-color)"),
    MEDIUM(1, "var(--app-layout-notification-highlight-color)"),
    LOW(0, "var(--app-layout-notification-highlight-color)");

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