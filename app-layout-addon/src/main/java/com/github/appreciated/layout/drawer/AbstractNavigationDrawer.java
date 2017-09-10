package com.github.appreciated.layout.drawer;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;

import java.io.IOException;


public abstract class AbstractNavigationDrawer extends CustomLayout {

    private final VerticalLayout contentHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();

    AppLayoutConnector connector = new AppLayoutConnector();

    public AbstractNavigationDrawer(String filename) throws IOException {
        super(AbstractNavigationDrawer.class.getResourceAsStream(filename));
        contentHolder.setMargin(false);
        menuElementHolder.setMargin(false);
        setSizeFull();
        addStyleName(getStyleName());
        super.addComponent(contentHolder, "content");
        super.addComponent(menuElementHolder, "menu-elements");
        addComponent(connector);
    }

    @Override
    public void addComponent(Component c) {
        contentHolder.addComponent(c);
    }

    public abstract String getStyleName();

    public void addNavigationElement(Component component) {
        menuElementHolder.addComponent(component);
    }

    public VerticalLayout getContentHolder() {
        return contentHolder;
    }

    public VerticalLayout getMenuElementHolder() {
        return menuElementHolder;
    }
}
