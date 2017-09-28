package com.github.appreciated.app.layout.builder.entities;


import java.util.ArrayList;

public class DefaultBadgeHolder {

    private int count;
    private ArrayList<BadgeListener> listeners = new ArrayList<>();

    public DefaultBadgeHolder() {

    }

    public DefaultBadgeHolder(int count) {
        this.count = count;
    }

    public void increase() {
        count++;
        listeners.forEach(listener -> listener.onChange(this));
    }

    public void decrease() {
        if (count > 0) {
            count--;
        }
        listeners.forEach(listener -> listener.onChange(this));
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        listeners.forEach(listener -> listener.onChange(this));
    }

    public void addListener(BadgeListener listener) {
        listeners.add(listener);
    }


    public interface BadgeListener {
        void onChange(DefaultBadgeHolder newStatus);
    }

}
