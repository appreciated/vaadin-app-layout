package com.github.appreciated.app.layout.behaviour.impl;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.builder.AppLayoutBehaviour;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class LeftFallBack extends VerticalLayout implements AppLayout {

    private final Panel contentPanel = new Panel();

    private final VerticalLayout menuHeaderHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();
    private final Panel menuElementPanel = new Panel(menuElementHolder);
    private final VerticalLayout menuFooterHolder = new VerticalLayout();

    private final VerticalLayout menuHolder = new VerticalLayout(menuHeaderHolder, menuElementPanel, menuFooterHolder);

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private final Label title = new Label("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title);

    public LeftFallBack(AppLayoutBehaviour variant) {
        this.setSpacing(false);
        this.setMargin(false);
        setSizeFull();
        contentPanel.setSizeFull();
        contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        menuHolder.setHeight(100, Unit.PERCENTAGE);
        if (!variant.isSmall()) {
            menuHolder.setWidth(256, Unit.PIXELS);
        } else {
            menuHolder.setWidth(64, Unit.PIXELS);
        }
        menuHolder.setMargin(false);
        menuHolder.setSpacing(false);
        menuHolder.setExpandRatio(menuElementPanel, 1);

        menuElementPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        menuElementPanel.setSizeFull();
        menuHeaderHolder.setVisible(false);
        menuFooterHolder.setVisible(false);
        menuHeaderHolder.setMargin(new MarginInfo(true, false, false, false));
        menuElementHolder.setMargin(new MarginInfo(true, false));
        menuFooterHolder.setMargin(new MarginInfo(false, false, true, false));
        menuElementHolder.setWidth(100, Unit.PERCENTAGE);
        addStyleName(getStyleName());
        if (variant.isOverlay()) {
            VerticalLayout vwrapper;
            if (variant.hasAppBar()) {
                vwrapper = new VerticalLayout(appBar, contentPanel);
            } else {
                vwrapper = new VerticalLayout(contentPanel);
            }
            vwrapper.setMargin(false);
            vwrapper.setSpacing(false);
            vwrapper.setExpandRatio(contentPanel, 1.0f);
            HorizontalLayout wrapper = new HorizontalLayout(menuHolder, vwrapper);
            wrapper.setExpandRatio(vwrapper, 1.0f);
            menuHolder.addStyleName("behaviour-content");
            if (variant.isSmall()) {
                menuHolder.addStyleName("small");
            }
            wrapper.setSizeFull();
            wrapper.setSpacing(false);
            addComponent(wrapper);
        } else {
            if (variant.hasAppBar()) {
                addComponent(appBar);
            }
            HorizontalLayout wrapper = new HorizontalLayout(menuHolder, contentPanel);
            menuHolder.addStyleName("behaviour-content");
            if (variant.isSmall()) {
                menuHolder.addStyleName("small");
            }
            wrapper.setSizeFull();
            wrapper.setSpacing(false);
            addComponent(wrapper);
            wrapper.setExpandRatio(contentPanel, 1.0f);
            setExpandRatio(wrapper, 1.0f);
        }
        appBar.addComponents(titleWrapper, appBarElementWrapper);
        appBar.setExpandRatio(appBarElementWrapper, 1);
        appBar.setWidth(100, Unit.PERCENTAGE);
        appBar.setHeight(64, Unit.PIXELS);
        appBar.addStyleName("app-toolbar");
        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.setSizeFull();
        appBarElementWrapper.addComponentAsFirst(appBarElementContainer);
        appBarElementContainer.setHeight(100, Unit.PERCENTAGE);
        appBarElementWrapper.setComponentAlignment(appBarElementContainer, Alignment.TOP_RIGHT);
        titleWrapper.setHeight(100, Unit.PERCENTAGE);
        titleWrapper.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
    }

    public String getStyleName() {
        return "left-fallback";
    }

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

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    public Panel getContentHolder() {
        return contentPanel;
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

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.addComponentAsFirst(appBarIconComponent);
        titleWrapper.setComponentAlignment(appBarIconComponent, Alignment.MIDDLE_LEFT);
    }
}
