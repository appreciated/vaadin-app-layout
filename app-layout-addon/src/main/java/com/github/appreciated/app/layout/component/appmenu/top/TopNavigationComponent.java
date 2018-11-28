package com.github.appreciated.app.layout.component.appmenu.top;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTab;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.router.Route;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that clicks on instances
 * on {@link TopNavigationComponent} respectively their {@link Component} which will usually causes a change of the View at the AppLayout content view.
 */
public class TopNavigationComponent extends PaperTab implements NavigationElementContainer {

    /**
     * The caption of this menu element
     */
    private String caption;
    /**
     * The respective view behind this menu element (either {@link LeftNavigationComponent#view} or {@link LeftNavigationComponent#className} will be initialized)
     */
    private Component view;
    /**
     * The view behind this menu element
     */
    private Icon icon;
    /**
     * The respective view behind this menu element (either {@link LeftNavigationComponent#view} or {@link LeftNavigationComponent#className} will be initialized)
     */
    private Class<? extends Component> className;

    public TopNavigationComponent(String caption, Icon icon, Component view) {
        super(caption, icon);
        this.caption = caption;
        this.icon = icon;
        this.view = view;
        setClickListener(appMenuIconItemClickEvent -> navigateTo());
    }

    public TopNavigationComponent(String caption, Icon icon, Class<? extends Component> className) {
        super(caption, icon);
        this.caption = caption;
        this.icon = icon;
        this.className = className;
        setClickListener(appMenuIconItemClickEvent -> navigateTo());
    }

    public void navigateTo() {
        UI.getCurrent().navigate(getRoute());
    }

    public String getRoute() {
        if (className != null) {
            return className.getAnnotation(Route.class).value();
        } else if (view != null) {
            return view.getClass().getAnnotation(Route.class).value();
        } else {
            return getCaption();
        }
    }

    private String getCaption() {
        if (caption != null) {
            return caption;
        }
        return null;
    }

    public Icon getIcon() {
        if (icon != null) {
            return icon;
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
    public boolean setActiveNavigationComponent(Class<? extends HasElement> element) {
        if (getViewClassName() == element) {
            setActive();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Component getComponent() {
        return null;
    }
}
