package com.github.appreciated.app.layout.component.menu.top.item;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.router.navigation.UpNavigationHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.HighlightAction;
import com.vaadin.flow.router.RouterLink;

public class TopNavigationLink extends RouterLink implements NavigationElement {
    private Class<? extends Component> view;
    private NavigationElementContainer parent;

    public TopNavigationLink(String caption, Icon icon, Class<? extends Component> view) {
        super();
        this.view = view;
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
        UpNavigationHelper.registerNavigationRoute(view);
        setRoute(UI.getCurrent().getRouter(), view);
        setHighlightCondition((routerLink, event) -> UpNavigationHelper.shouldHighlight(view, event));
        HighlightAction<RouterLink> action = getHighlightAction();
        setHighlightAction((routerLink, highlight) -> {
            action.highlight(routerLink, highlight);
            if (parent != null && highlight) {
                parent.setActiveNavigationElement(this);
            }
        });
    }


    @Override
    public void setNavigationElementContainer(NavigationElementContainer parent) {
        this.parent = parent;
    }
}
