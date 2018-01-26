package com.github.appreciated.app.layout.builder.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Class is the controller of a Component that can display a simple badge containing numbers
 */
public class DefaultBadgeHolder implements Serializable {

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

    public interface BadgeListener extends Serializable {
        void onChange(DefaultBadgeHolder newStatus);
    }

}
