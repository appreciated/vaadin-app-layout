package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.component.VerticalFlexBoxLayout;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.IOException;


public abstract class AbstractLeftAppLayout extends CustomLayout implements AppLayout {

    private final Panel contentPanel = new Panel();

    private final VerticalLayout menuHeaderHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();
    private final VerticalLayout menuFooterHolder = new VerticalLayout();

    private final VerticalFlexBoxLayout menuHolder = new VerticalFlexBoxLayout(menuHeaderHolder, menuElementHolder, menuFooterHolder);

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private final Label title = new Label("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title);

    public AbstractLeftAppLayout(String filename) throws IOException {
        super(AbstractLeftAppLayout.class.getResourceAsStream(filename));
        setSizeFull();
        contentPanel.setSizeFull();
        contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        menuHolder.setSizeFull();
        menuHolder.grow(menuElementHolder);
        menuHolder.setOverflowAuto(true);

        menuHeaderHolder.setVisible(false);
        menuFooterHolder.setVisible(false);
        menuHeaderHolder.setMargin(false);
        menuElementHolder.setMargin(new MarginInfo(true, false));
        menuFooterHolder.setMargin(new MarginInfo(false, false, true, false));
        menuElementHolder.setWidth(100, Unit.PERCENTAGE);
        addStyleName("app-layout-behaviour-" + getStyleName());
        addComponent(contentPanel, "content");
        addComponent(menuHolder, "menu-elements");
        addComponent(appBar, "app-bar-elements");
        appBar.addComponents(titleWrapper, appBarElementWrapper);
        appBar.setExpandRatio(appBarElementWrapper, 1);
        appBar.setWidth(100, Unit.PERCENTAGE);
        appBar.setHeight(100, Unit.PERCENTAGE);
        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.setSizeFull();
        appBarElementWrapper.addComponentAsFirst(appBarElementContainer);
        appBarElementContainer.setHeight(100, Unit.PERCENTAGE);
        appBarElementWrapper.setComponentAlignment(appBarElementContainer, Alignment.TOP_RIGHT);
        titleWrapper.setHeight(100, Unit.PERCENTAGE);
        titleWrapper.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
    }

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
        appBarElementContainer.addComponent(component);
        appBarElementContainer.setComponentAlignment(component, Alignment.MIDDLE_RIGHT);
    }

    public void setDesign(AppBarDesign design) {
        this.addStyleName(design.getStylename());
    }

    public HorizontalLayout getAppBar() {
        return appBar;
    }

    public HorizontalLayout getAppBarElementWrapper() {
        return appBarElementWrapper;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    @Override
    public Panel getContentHolder() {
        return contentPanel;
    }

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
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

    public Layout getMenuHolder() {
        return menuHolder;
    }

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.addComponentAsFirst(appBarIconComponent);
        titleWrapper.setComponentAlignment(appBarIconComponent, Alignment.MIDDLE_LEFT);
    }
}
