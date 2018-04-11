package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Position;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementClickListener;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.exception.ViewNameMissingException;
import com.github.appreciated.app.layout.navigator.ComponentNavigator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on instances
 * on {@link NavigatorNavigationElement} respectively their {@link com.vaadin.ui.Component} will lead to a call of
 * {@link Navigator#navigateTo(String)} which usually causes a change of the View .
 */
public class NavigatorNavigationElement extends AbstractNavigationElement<NavigationElementComponent, NavigatorNavigationElement> implements HasCaptionInterceptor, Button.ClickListener {
    private boolean isCDI = false;

    /**
     * The caption of this menu element
     */
    private String caption;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private View view;
    /**
     * The view behind this menu element
     */
    private Resource icon;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private Class<? extends View> className;
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

    /**
     * The {@link Navigator} instance to which the view will be added
     */
    private Navigator navigator;

    /**
     * The {@link ComponentNavigator} instance to which the view will be added.
     */
    private ComponentNavigator componentNavigator;

    /**
     *
     */
    NavigationElementClickListener clickListener;

    public NavigatorNavigationElement(String caption, Resource icon, View view) {
        this(caption, null, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this(caption, null, icon, badgeHolder, view);
    }

    public NavigatorNavigationElement(String caption, String path, Resource icon, DefaultBadgeHolder badgeHolder, View view) {
        this.caption = caption;
        this.icon = icon;
        this.path = path;
        this.badgeHolder = badgeHolder;
        this.view = view;
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public NavigatorNavigationElement(String caption, Resource icon, Class<? extends View> className) {
        this(caption, null, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Resource icon, DefaultBadgeHolder badgeHolder, Class<? extends View> className) {
        this(caption, null, icon, badgeHolder, className);
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
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public void addViewToNavigator(Navigator navigator) {
        try {
            this.navigator = navigator;
            if (!isCDI) { // Since adding the views to the navigator will be done by the cdi framework its not necessary to so
                if (getViewName() != null) {
                    if (view != null) {
                        navigator.addView(getViewName(), view);
                    } else if (className != null) {
                        navigator.addView(getViewName(), className);
                    }
                } else {
                    throw new ViewNameMissingException(getViewClassName());
                }
            }
        } catch (ViewNameMissingException e) {
            e.printStackTrace();
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
    public void setProvider(AppLayoutComponent provider) {
        setProvider(provider.getDrawerNavigationElementProvider());
    }

    @Override
    public void setProvider(AppLayoutComponent provider, Position position) {
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
            throw new IllegalStateException("Please set a NavigationElementInfoProvider via withNavigationElementInfoProducer for the Injected Views");
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

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        if (navigator != null) {
            navigator.navigateTo(getViewName());
        } else if (componentNavigator != null) {
            componentNavigator.navigateTo(getViewName());
        }
        if (clickListener != null) {
            clickListener.onClick(this, clickEvent);
        }
    }

    public void setClickListner(NavigationElementClickListener listener) {
        this.clickListener = listener;
    }
}
