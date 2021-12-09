package com.github.appreciated.app.layout.component.menu.top.item;

import com.github.appreciated.app.layout.component.builder.interfaces.NavigationElement;
import com.github.appreciated.app.layout.component.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.navigation.UpNavigationHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;

public class TopNavigationLink extends RouterLink implements NavigationElement, BeforeEnterObserver {
    private static final long serialVersionUID = 1L;

    private Class<? extends Component> className;
    private NavigationElementContainer parent;

    public TopNavigationLink(String caption, Component icon, Class<? extends Component> className) {
        this(caption, icon, className, RouteParameters.empty());
    }

    public TopNavigationLink(String caption, Component icon, Class<? extends Component> className, RouteParameters parameters) {
        super();
        this.className = className;
        HorizontalLayout wrapper = new HorizontalLayout();
        if (icon != null){
            wrapper.add(icon);
        }
        if(caption != null){
            wrapper.add(new Label(caption));
        }
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        wrapper.setHeight("100%");
        add(wrapper);
        UpNavigationHelper.registerNavigationRoute(className, parameters);
        setRoute(className, parameters);
        setHighlightCondition((routerLink, event) -> UpNavigationHelper.shouldHighlight(className, event.getLocation()));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (parent != null) {
            parent.setActiveNavigationElement(UpNavigationHelper.shouldHighlight(className, event.getLocation()) ? this : null);
        }
    }

    @Override
    public void setNavigationElementContainer(NavigationElementContainer parent) {
        this.parent = parent;
    }
}
