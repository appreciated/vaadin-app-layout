package com.github.appreciated.app.layout.notification;

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
