package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.behaviour.Position;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on instances
 * on {@link NavigatorNavigationElement} respectively their {@link com.vaadin.ui} will lead to a call of
 * which usually causes a change of the View .
 */
public class NavigatorNavigationElement extends AbstractNavigationElement<NavigationElementComponent, NavigatorNavigationElement> implements HasCaptionInterceptor {
    private boolean isCDI = false;

    /**
     * The caption of this menu element
     */
    private String caption;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private HasElement view;
    /**
     * The view behind this menu element
     */
    private Icon icon;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private Class<? extends HasElement> className;
    /**
     * The (url-)path under which this view will available
     */
    private String path;
    /**
     * A badge holder which holds the controls view state of the {@link NavigationElementComponent}
     * Note: May be null
     */
    private DefaultBadgeHolder badgeHolder;

    /**
     * The view name interceptor that allows replace the view name before initializing the navigator cannot be used when using cdi
     * This can come in handy in some situations where the view name does not conform a proper URL-encoding
     * Note: May be null
     */
    private Factory<String, String> viewNameInterceptor;
    /**
     * The {@link com.github.appreciated.app.layout.builder.AppLayoutConfiguration.NavigationElementInfoProducer} instance
     * which will eventuall later on be used to provide the caption, view name and icon for this menu element for the View / view class.
     * Note: May be null
     */
    private AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider;
    private NavigationElementInfo info;

    /**
     * The view name interceptor that allows replace the caption of each menu element that has one before initializing.
     * This can f.e. be used to replace I18N string with their localized string value
     * Note: May be null
     */
    private Factory<String, String> captionInterceptor;

    public NavigatorNavigationElement(String caption, Icon icon, HasElement view) {
        this(caption, null, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, Icon icon, DefaultBadgeHolder badgeHolder, HasElement view) {
        this(caption, null, icon, badgeHolder, view);
    }

    public NavigatorNavigationElement(String caption, String path, Icon icon, DefaultBadgeHolder badgeHolder, HasElement view) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.view = view;
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public NavigatorNavigationElement(String caption, Icon icon, Class<? extends HasElement> className) {
        this(caption, null, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends HasElement> className) {
        this(caption, null, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(Icon icon, Class<? extends HasElement> className) {
        this(null, null, icon, null, className);
    }

    public NavigatorNavigationElement(Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends HasElement> className) {
        this(null, null, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(String caption, String path, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends HasElement> className) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.className = className;
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public void addViewToNavigator() {
/*
        this.navigator = navigator;
        if (!isCDI) { // Since adding the views to the navigator will be done by the cdi framework its not necessary to so
            if (view != null) {
                navigator.addView(getViewName(), view);
            } else if (className != null) {
                navigator.addView(getViewName(), className);
            }
        }*/
    }

    public String getCaption() {
        if (captionInterceptor == null) {
            return caption;
        } else {
            return captionInterceptor.get(caption);
        }
    }

    public String getViewName() {
        if (!isCDI) { // since cdi does not allow changing the viewname on runtime the interceptor must not have an effect
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

    public Icon getIcon() {
        return icon;
    }

    public HasElement getView() {
        return view;
    }

    public Class<? extends HasElement> getViewClassName() {
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
    public void setProvider(AppLayoutElementBase provider) {
        setProvider(provider.getDrawerNavigationElementProvider());
    }

    @Override
    public void setProvider(AppLayoutElementBase provider, Position position) {
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

    public void setViewNameInterceptor(Factory<String, String> viewNameInterceptor) {
        if (isCDI && viewNameInterceptor != null) {
            throw new IllegalStateException("It is not possible to use the ViewNameInterceptor in combination with CDI");
        } else {
            this.viewNameInterceptor = viewNameInterceptor;
        }
    }

    public void setCaptionInterceptor(Factory<String, String> captionInterceptor) {
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
        UI.getCurrent().navigate(getViewName());
    }
}
