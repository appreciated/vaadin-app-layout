package com.github.appreciated.applayout.entity;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the controller of a Component that can display a simple badge containing numbers also it allows the user
 * to update the view by updating this controller
 */
public class DefaultBadgeHolder {

    private int count;
    private ArrayList<BadgeListener> listeners = new ArrayList<>();

    private List<HasText> badgeHolderComponents = new ArrayList<>();

    public DefaultBadgeHolder() {

    }

    public DefaultBadgeHolder(int count) {
        this.count = count;
    }

    public void increase() {
        count++;
        listeners.forEach(listener -> listener.onChange(this));
        updateBadgeCaptions();
    }

    public void decrease() {
        if (count > 0) {
            count--;
        }
        listeners.forEach(listener -> listener.onChange(this));
        updateBadgeCaptions();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        listeners.forEach(listener -> listener.onChange(this));
        updateBadgeCaptions();
    }

    public void clearCount() {
        setCount(0);
    }

    public void addListener(BadgeListener listener) {
        listeners.add(listener);
    }

    public void bind(HasText text) {
        addBadgeHolderComponent(text);
    }

    private void addBadgeHolderComponent(HasText text) {
        this.badgeHolderComponents.add(text);
        updateBadgeCaption(text);
    }

    private void updateBadgeCaptions() {
        badgeHolderComponents.forEach(hasText -> updateBadgeCaption(hasText));
    }

    private void updateBadgeCaption(HasText hasText) {
        if (hasText != null) {
            hasText.setText(String.valueOf(getCount()));
            if (hasText instanceof Component) {
                ((Component) hasText).setVisible(getCount() > 0);
            }
        }
    }

    public interface BadgeListener {
        void onChange(DefaultBadgeHolder newStatus);
    }

}
