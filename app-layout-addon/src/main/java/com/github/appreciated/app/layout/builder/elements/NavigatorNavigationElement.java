package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.Provider;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.impl.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

import java.util.Optional;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on
 * MenuElements of this Type  will lead to the Navigator being called.
 */
public class NavigatorNavigationElement extends AbstractNavigationElement<NavigationElementComponent, NavigatorNavigationElement> {
    private boolean isCDI = false;
    private String caption;
    private View view;
    private Resource icon;
    private Class<? extends View> className;
    private String path;
    private DefaultBadgeHolder badgeHolder;
    private Provider<String, String> viewNameInterceptor;
    private AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider;
    private NavigationElementInfo info;
    private Provider<String, String> captionInterceptor;
    private Optional<SubmenuNavigationElement> parent = Optional.empty();
    private Navigator navigator;
    private ComponentNavigator componentNavigator;

    public NavigatorNavigationElement(String caption, Resource icon, View view) {
        this(caption, caption, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this(caption, caption, icon, badgeHolder, view);
    }

    public NavigatorNavigationElement(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.view = view;
        provider = new DefaultLeftNavigationBadgeElementComponentProvider();
    }

    public NavigatorNavigationElement(String caption, Resource icon, Class<? extends View> className) {
        this(caption, caption, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this(caption, caption, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(Resource icon, Class<? extends View> className) {
        this(null, null, icon, null, className);
    }

    public NavigatorNavigationElement(Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this(null, null, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.className = className;
        provider = new DefaultLeftNavigationBadgeElementComponentProvider();
    }

    public void addViewToNavigator(Navigator navigator) {

        this.navigator = navigator;
        if (!isCDI) { // Since adding the views to the navigator will be done by the cdi framework its not necessary to so
            if (view != null) {
                navigator.addView(getViewName(), view);
            } else if (className != null) {
                navigator.addView(getViewName(), className);
            }
        }
    }

    public void addViewToComponentNavigator(ComponentNavigator navigator) {

        this.componentNavigator = navigator;
        if (view != null) {
            navigator.addView(getViewName(), view);
        } else if (className != null) {
            navigator.addView(getViewName(), className);
        }
    }

    public String getCaption() {
        if (captionInterceptor == null) {
            return caption;
        } else {
            return captionInterceptor.get(caption);
        }
    }

    public String getViewName() {
        if (!isCDI) {
            if (viewNameInterceptor != null) {
                if (path != null) {
                    return viewNameInterceptor.get(path);
                } else if (info != null) {
                    return viewNameInterceptor.get(info.getViewName());
                } else {
                    return viewNameInterceptor.get(caption);
                }
            }
        }
        if (path != null) {
            return path;
        } else if (info != null) {
            return info.getViewName();
        } else {
            return caption;
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

    @Override
    public void setProvider(AppLayout provider) {
        setProvider(provider.getDrawerNavigationElementProvider());
    }

    @Override
    public void setProvider(AppLayout provider, AppLayout.Position position) {
        switch (position) {
            case DRAWER:
                setProvider(provider.getDrawerNavigationElementProvider());
                break;
            case TOP:
                setProvider(provider.getTopNavigationElementProvider());
                break;
        }
    }

    public DefaultBadgeHolder getBadgeHolder() {
        return badgeHolder;
    }

    public void setViewNameInterceptor(Provider<String, String> viewNameInterceptor) {
        if (isCDI && viewNameInterceptor != null) {
            throw new IllegalStateException("It is not possible to use the ViewNameInterceptor in combination with CDI");
        } else {
            this.viewNameInterceptor = viewNameInterceptor;
        }
    }

    public void setCaptionInterceptor(Provider<String, String> captionInterceptor) {
        this.captionInterceptor = captionInterceptor;
    }

    public void setNavigationElementInfoProvider(AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider) {
        if (caption == null && icon == null && className == null && navigationElementInfoProvider == null) {
            throw new IllegalStateException("Please set a NavigationElementInfoProvider via withNavigationElementInfoProvider for the Injected Views");
        } else {
            this.navigationElementInfoProvider = navigationElementInfoProvider;
            refreshInfo();
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

    public boolean isCDI() {
        return isCDI;
    }

    public void setCDI(boolean CDI) {
        if (CDI) {
            if (view != null) {
                throw new IllegalStateException("View must not be set if using CDI! Add your CDI views as Class or disable CDI");
            }
        }
        isCDI = CDI;
    }

    public void onClick() {
        if (navigator != null) {
            navigator.navigateTo(getViewName());
        } else if (componentNavigator != null) {
            componentNavigator.navigateTo(getViewName());
        }
    }
}
