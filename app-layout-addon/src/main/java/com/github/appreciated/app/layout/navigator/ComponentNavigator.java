package com.github.appreciated.app.layout.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Panel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

/**
 * A very basic implementation of the Navigator functionality to allow users using this addon without the navigator
 */
public class ComponentNavigator {

    private View errorView;
    private List<ViewChangeListener> listeners = new ArrayList<>();
    private HashMap<String, View> views = new HashMap<>();
    private String currentViewName = null;
    private Panel contentHolder;

    public ComponentNavigator(Panel contentHolder) {
        this.contentHolder = contentHolder;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public void addView(String viewName, View view) {
        if (!views.containsKey(viewName)) {
            views.put(viewName, view);
        }
    }

    public void addView(String viewName, Class<? extends View> view) {
        if (!views.containsKey(viewName)) {
            try {
                views.put(viewName, view.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Registration addViewChangeListener(ViewChangeListener listener) {
        this.listeners.add(listener);
        return () -> this.listeners.remove(listener);
    }

    public void navigateTo(String viewName) {
        if (currentViewName == null || !currentViewName.equals(viewName)) {
            if (views.containsKey(viewName)) {
                contentHolder.setContent(views.get(viewName).getViewComponent());
                this.listeners.forEach(viewChangeListener -> viewChangeListener.beforeViewChange(new ViewChangeListener.ViewChangeEvent(this, views.getOrDefault(currentViewName, null), views.get(viewName), viewName, null)));
                currentViewName = viewName;
            } else {
                currentViewName = null;
                if (errorView != null) {
                    contentHolder.setContent(errorView.getViewComponent());
                } else {
                    throw new RuntimeException("You tried to navigate to a viewName that wasn't added");
                }
            }
        }
    }

    @FunctionalInterface
    public interface ViewChangeListener extends Serializable {
        boolean beforeViewChange(ViewChangeListener.ViewChangeEvent var1);

        class ViewChangeEvent extends EventObject {
            private final View oldView;
            private final View newView;
            private final String viewName;
            private final String parameters;

            public ViewChangeEvent(ComponentNavigator navigator, View oldView, View newView, String viewName, String parameters) {
                super(navigator);
                this.oldView = oldView;
                this.newView = newView;
                this.viewName = viewName;
                this.parameters = parameters;
            }

            public Navigator getNavigator() {
                return (Navigator) this.getSource();
            }

            public View getOldView() {
                return this.oldView;
            }

            public View getNewView() {
                return this.newView;
            }

            public String getViewName() {
                return this.viewName;
            }

            public String getParameters() {
                return this.parameters;
            }
        }
    }

}
