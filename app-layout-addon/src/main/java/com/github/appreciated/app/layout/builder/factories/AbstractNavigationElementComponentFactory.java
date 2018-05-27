package com.github.appreciated.app.layout.builder.factories;

import com.github.appreciated.app.layout.builder.elements.NavigatorNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.button.NavigationBadgeIconButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.Element;

public abstract class AbstractNavigationElementComponentFactory implements ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> {

    public void setNavigationClickListener(NavigatorNavigationElement element) {
        NavigationBadgeIconButton button = (NavigationBadgeIconButton) element.getComponent();
        button.setClickListener(clickEvent -> {
            element.onClick();
            //UI.getCurrent().getRouter()..closeDrawerIfNotPersistent();
            Element celement = UI.getCurrent().getElement();
            if (celement.getComponent().get() instanceof AppLayoutRouterLayout) {
                AppLayoutRouterLayout layout = (AppLayoutRouterLayout) celement.getComponent().get();
                layout.closeDrawer();
            }
        });


    }
}
