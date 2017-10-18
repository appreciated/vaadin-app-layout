package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.interceptor.ViewNameInterceptor;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class NavigatorNavigationElement extends AbstractNavigationElement<NavigatorNavigationElement> {
    private final String name;
    private View view;
    private Resource icon;
    private Class<? extends View> className;
    private DefaultBadgeHolder badgeHolder;
    private String navigationName;
    private ViewNameInterceptor viewNameInterceptor;

    public NavigatorNavigationElement(String name, Resource icon, Class<? extends View> className) {
        this(name, icon, null, className);
    }

    public NavigatorNavigationElement(String name, Resource icon, View view) {
        this(name, icon, null, view);
    }

    public NavigatorNavigationElement(String name, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this.name = name;
        this.icon = icon;
        this.badgeHolder = badgeHolder;
        this.className = className;
    }

    public NavigatorNavigationElement(String name, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this.name = name;
        this.badgeHolder = badgeHolder;
        this.icon = icon;
        this.view = view;
    }

    public NavigatorNavigationElement(NavigationElementInfo info) {
        this.name = info.getCaption();
        this.icon = info.getIcon();
        this.navigationName = info.getViewName();
    }

    public void addViewToNavigator(Navigator navigator) {
        if (view != null) {
            navigator.addView(getViewName(), view);
        } else if (className != null) {
            navigator.addView(getViewName(), className);
        }
    }

    public void setAsDefaultView(Navigator navigator) {
        if (view != null) {
            navigator.addView("", view);
        } else if (className != null) {
            navigator.addView("", className);
        }
    }

    public String getName() {
        return name;
    }

    public String getViewName() {
        if (viewNameInterceptor == null) {
            return name;
        } else {
            return viewNameInterceptor.get(name);
        }
    }

    public Resource getIcon() {
        return icon;
    }

    public View getView() {
        return view;
    }

    public Class<? extends View> getViewClassName() {
        if (className == null) {
            return view == null ? null : view.getClass();
        } else {
            return className;
        }
    }


    @Override
    NavigatorNavigationElement getInfo() {
        return this;
    }

    public DefaultBadgeHolder getBadgeHolder() {
        return badgeHolder;
    }

    public void setViewNameInterceptor(ViewNameInterceptor viewNameInterceptor) {
        this.viewNameInterceptor = viewNameInterceptor;
    }
}
