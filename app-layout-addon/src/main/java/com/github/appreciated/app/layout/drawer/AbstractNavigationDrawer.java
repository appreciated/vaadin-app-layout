package com.github.appreciated.app.layout.drawer;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.IOException;


public abstract class AbstractNavigationDrawer extends CustomLayout {

    private final VerticalLayout contentHolder = new VerticalLayout();
    private final Panel contentPanel = new Panel(contentHolder);

    private final VerticalLayout menuHeaderHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();
    private final Panel menuElementPanel = new Panel(menuElementHolder);
    private final VerticalLayout menuFooterHolder = new VerticalLayout();

    private final VerticalLayout menuHolder = new VerticalLayout(menuHeaderHolder, menuElementPanel, menuFooterHolder);

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementHolder = new HorizontalLayout();
    private final Label title = new Label("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title);

    public static void toggleDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').toggle();");
    }

    public static void openDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').open();");
    }

    public static void closeDrawerIfNotPersistent() {
        Page.getCurrent().getJavaScript().execute("if(!document.querySelector('app-drawer').hasAttribute('persistent')){document.querySelector('app-drawer').close();}");
    }

    public static void closeDrawer() {
        Page.getCurrent().getJavaScript().execute("document.querySelector('app-drawer').close();");
    }

    public AbstractNavigationDrawer(String filename) throws IOException {
        super(AbstractNavigationDrawer.class.getResourceAsStream(filename));
        setSizeFull();
        contentPanel.setSizeFull();
        contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        menuHolder.setSizeFull();
        menuHolder.setMargin(false);
        menuHolder.setSpacing(false);
        menuHolder.setExpandRatio(menuElementPanel, 1);

        menuElementPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        menuElementPanel.setSizeFull();
        menuHeaderHolder.setVisible(false);
        menuFooterHolder.setVisible(false);
        menuHeaderHolder.setMargin(false);
        menuElementHolder.setMargin(false);
        menuFooterHolder.setMargin(false);
        menuElementHolder.setWidth(100, Unit.PERCENTAGE);
        addStyleName(getStyleName());
        addComponent(contentPanel, "content");
        addComponent(menuHolder, "menu-elements");
        addComponent(appBar, "app-bar-elements");
        appBar.addComponents(titleWrapper, appBarElementHolder);
        appBar.setExpandRatio(appBarElementHolder, 1);
        appBar.setWidth(100, Unit.PERCENTAGE);
        appBar.setHeight(100, Unit.PERCENTAGE);
        appBar.setComponentAlignment(appBarElementHolder, Alignment.MIDDLE_RIGHT);
        appBarElementHolder.setSpacing(false);
        titleWrapper.setHeight(100, Unit.PERCENTAGE);
        titleWrapper.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
    }

    public abstract String getStyleName();

    public void addNavigationHeaderElement(Component component) {
        menuHeaderHolder.setVisible(true);
        menuHeaderHolder.addComponent(component);
    }

    public void addNavigationFooterElement(Component component) {
        menuFooterHolder.setVisible(true);
        menuFooterHolder.addComponent(component);
    }

    public void addNavigationElement(Component component) {
        menuElementHolder.addComponent(component);
    }

    public void addAppBarElement(Component component) {
        appBarElementHolder.addComponent(component);
    }

    public void setDesign(AppBarDesign design) {
        this.addStyleName(design.getStylename());
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

    public void setTitle(String title) {
        this.title.setValue(title);
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

    public VerticalLayout getMenuFooterHolder() {
        return menuFooterHolder;
    }

    public VerticalLayout getMenuHeaderHolder() {
        return menuHeaderHolder;
    }

    public VerticalLayout getMenuHolder() {
        return menuHolder;
    }
}
