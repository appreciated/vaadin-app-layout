package com.github.appreciated.app.layout.builder.entities;

import java.util.ArrayList;

/**
 * This Class is the controller of a Component that can display a simple badge containing numbers also it allows the user
 * to update the view by updating this controller
 */
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

    public void clearCount() {
        setCount(0);
    }

    public void addListener(BadgeListener listener) {
        listeners.add(listener);
    }

    public interface BadgeListener {
        void onChange(DefaultBadgeHolder newStatus);
    }

}
