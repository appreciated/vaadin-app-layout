package com.github.appreciated.layout.drawer;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

import java.io.IOException;


public abstract class AbstractNavigationDrawer extends CustomLayout {

    private final VerticalLayout contentHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();
    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementHolder = new HorizontalLayout();
    private final Label title = new Label("Title");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title);
    AppLayoutConnector connector = new AppLayoutConnector();

    public AbstractNavigationDrawer(String filename) throws IOException {
        super(AbstractNavigationDrawer.class.getResourceAsStream(filename));
        contentHolder.setMargin(false);
        menuElementHolder.setMargin(false);
        setSizeFull();
        addStyleName(getStyleName());
        super.addComponent(contentHolder, "content");
        super.addComponent(menuElementHolder, "menu-elements");
        super.addComponent(appBar, "app-bar-elements");
        appBar.addComponents(titleWrapper, appBarElementHolder);
        appBar.setSizeFull();
        appBar.setComponentAlignment(appBarElementHolder,Alignment.TOP_RIGHT);
        titleWrapper.setHeight(100,Unit.PERCENTAGE);
        titleWrapper.setComponentAlignment(title,Alignment.MIDDLE_LEFT);
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
    public void addAppBarElement(Component component) {
        appBarElementHolder.addComponent(component);
    }

    public HorizontalLayout getAppBar() {
        return appBar;
    }

    public HorizontalLayout getAppBarElementHolder() {
        return appBarElementHolder;
    }

    public Label getTitle() {
        return title;
    }

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    public VerticalLayout getContentHolder() {
        return contentHolder;
    }

    public VerticalLayout getMenuElementHolder() {
        return menuElementHolder;
    }
}
