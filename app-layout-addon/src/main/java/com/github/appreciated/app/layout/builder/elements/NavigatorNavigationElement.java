package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.interceptor.ViewNameInterceptor;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;


public class NavigatorNavigationElement extends AbstractNavigationElement<NavigationElementComponent, NavigatorNavigationElement> {
    private boolean isManaged = false;
    private String caption;
    private View view;
    private Resource icon;
    private Class<? extends View> className;
    private String path;
    private DefaultBadgeHolder badgeHolder;
    private ViewNameInterceptor viewNameInterceptor;
    private AppLayoutConfiguration.NavigationElementInfoProvider navigationElementInfoProvider;
    private NavigationElementInfo info;


    public NavigatorNavigationElement(String caption, Resource icon, Class<? extends View> className) {
        this(caption, caption, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Resource icon, View view) {
        this(caption, caption, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this(caption, caption, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this(caption, caption, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.className = className;
    }

    public NavigatorNavigationElement(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this.caption = caption;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.icon = icon;
        this.view = view;
    }

    public NavigatorNavigationElement(Class<? extends View> className, Resource icon) {
        this.className = className;
        this.icon = icon;
        isManaged = true;
    }

    public void addViewToNavigator(Navigator navigator) {
        if (!isManaged) {
            if (view != null) {
                navigator.addView(getViewName(), view);
            } else if (className != null) {
                navigator.addView(getViewName(), className);
            }
        }
    }

    public void setAsDefaultView(Navigator navigator) {
        if (view != null) {
            navigator.addView("", view);
        } else if (className != null) {
            navigator.addView("", className);
        }
    }

    public String getCaption() {
        return caption;
    }

    public String getViewName() {
        if (isManaged) {
            return info.getViewName();
        } else if (viewNameInterceptor == null) {
            return path;
        } else {
            return viewNameInterceptor.get(caption);
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

    public void setNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProvider navigationElementInfoProvider) {
        if (isManaged) {
            if (navigationElementInfoProvider == null) {
                throw new IllegalStateException("Please set a NavigationElementInfoProvider via withNavigationElementInfoProvider for the Injected Views");
            } else {
                this.navigationElementInfoProvider = navigationElementInfoProvider;
                refreshInfo();
            }
        }
    }

    public void refreshInfo() {
        if (navigationElementInfoProvider != null) {
            info = navigationElementInfoProvider.apply(className);
            caption = info.getCaption();
            if (info.getIcon() != null) {
                this.icon = info.getIcon();
            }
        }
    }
}
