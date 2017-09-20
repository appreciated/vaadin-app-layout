package com.github.appreciated.app.layout.builder.entities;

import java.util.ArrayList;

public class BadgeStatus {

    private ArrayList<BadgeStatusListener> listeners = new ArrayList<>();
    private String status;

    public BadgeStatus() {
        this(null);
    }

    public BadgeStatus(String status) {
        this.status = status;
        listeners.forEach(listener -> listener.onStatusChange(this));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addStatusListener(BadgeStatusListener listener) {
        listeners.add(listener);
    }

    public interface BadgeStatusListener {
        void onStatusChange(BadgeStatus newStatus);
    }
}
