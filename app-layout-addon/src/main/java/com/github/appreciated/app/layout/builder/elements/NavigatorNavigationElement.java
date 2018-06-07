package com.github.appreciated.app.layout.builder.elements;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.NavigationElementInfo;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.HasCaptionInterceptor;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on instances
 * on {@link NavigatorNavigationElement} respectively their {@link com.vaadin.flow.component.Component} which will usually causes a change of the View at the AppLayout content view.
 */
public class NavigatorNavigationElement extends AbstractNavigationElement<NavigationElementComponent, NavigatorNavigationElement> implements HasCaptionInterceptor {
    /**
     * The caption of this menu element
     */
    private String caption;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private Component view;
    /**
     * The view behind this menu element
     */
    private Icon icon;
    /**
     * The respective view behind this menu element (either {@link NavigatorNavigationElement#view} or {@link NavigatorNavigationElement#className} will be initialized)
     */
    private Class<? extends Component> className;
    /**
     * The (url-)path under which this view will available
     */
    private String route;
    /**
     * A badge holder which holds the controls view state of the {@link NavigationElementComponent}
     * Note: May be null
     */
    private DefaultBadgeHolder badgeHolder;

    /**
     * The view name interceptor that allows replace the route before initializing the router cannot be used when using cdi
     * This can come in handy in some situations where the route does not conform a proper URL-encoding
     * Note: May be null
     */
    private Factory<String, String> routeInterceptor;
    /**
     * The {@link com.github.appreciated.app.layout.builder.AppLayoutConfiguration.NavigationElementInfoProducer} instance
     * which will eventually later on be used to provide the caption, route and icon for this menu element for the View / view class.
     * Note: May be null
     */
    private AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider;
    private NavigationElementInfo info;

    /**
     * The route interceptor that allows replace the caption of each menu element that has one before initializing.
     * This can f.e. be used to replace I18N string with their localized string value
     * Note: May be null
     */
    private Factory<String, String> captionInterceptor;

    public NavigatorNavigationElement(String caption, Icon icon, Component view) {
        this(caption, null, icon, null, view);
    }

    public NavigatorNavigationElement(String caption, Icon icon, DefaultBadgeHolder badgeHolder, Component view) {
        this(caption, null, icon, badgeHolder, view);
    }

    public NavigatorNavigationElement(String caption, String route, Icon icon, DefaultBadgeHolder badgeHolder, Component view) {
        this.caption = caption;
        this.icon = icon;
        this.route = route;
        this.badgeHolder = badgeHolder;
        this.view = view;
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public NavigatorNavigationElement(String caption, Icon icon, Class<? extends Component> className) {
        this(caption, null, icon, null, className);
    }

    public NavigatorNavigationElement(String caption, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends Component> className) {
        this(caption, null, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(Icon icon, Class<? extends Component> className) {
        this(null, null, icon, null, className);
    }

    public NavigatorNavigationElement(Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends Component> className) {
        this(null, null, icon, badgeHolder, className);
    }

    public NavigatorNavigationElement(String caption, String route, Icon icon, DefaultBadgeHolder badgeHolder, Class<? extends Component> className) {
        this.caption = caption;
        this.icon = icon;
        this.route = route;
        this.badgeHolder = badgeHolder;
        this.className = className;
        provider = new DefaultLeftNavigationBadgeElementComponentFactory();
    }

    public String getRoute() {
        if (route != null) {
            return route;
        } else if (info != null) {
            return info.getRoute();
        } else {
            return getCaption();
        }
    }

    public String getCaption() {
        if (caption != null) {
            return caption;
        } else if (info != null) {
            return info.getCaption();
        }
        return null;
    }

    public Icon getIcon() {
        if (icon != null) {
            return icon;
        } else if (info != null) {
            return info.getIcon();
        }
        return null;
    }

    public Component getView() {
        return view;
    }

    public Class<? extends Component> getViewClassName() {
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
        setProvider(provider.getNavigationElementProvider());
    }

    public DefaultBadgeHolder getBadgeHolder() {
        return badgeHolder;
    }

    public void setRouteInterceptor(Factory<String, String> routeInterceptor) {
        if (routeInterceptor != null) {
            this.routeInterceptor = routeInterceptor;
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
        }
    }

    public void onClick() {
        UI.getCurrent().navigate(getRoute());
    }

    public void initRouterInformation() {
        if (UI.getCurrent().getRouter().getRoutes().stream().filter(routeData -> routeData.getUrl().equals(getRoute())).count() == 0) {
            //UI.getCurrent().getRouter().getUrl(getViewClassName());
            System.err.println("The Menuelement with the route \"" + getRoute() + "\" cannot be navigated to since it wasn't added to the router. Currently it isn't possible to add new Views to the router dynamically (This will be possible in future)");
        }
    }
}
