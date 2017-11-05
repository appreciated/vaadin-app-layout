package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftClickableNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.left.DefaultLeftSubmenuNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopClickableNavigationElementProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopNavigationBadgeElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopSectionElementComponentProvider;
import com.github.appreciated.app.layout.builder.providers.top.DefaultTopSubmenuNavigationElementProvider;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

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
    private List<NavigatorNavigationElement> list;

    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> drawerNavigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
    private ComponentProvider<Component, SubmenuNavigationElement> drawerSubmenuElementProvider = new DefaultLeftSubmenuNavigationElementProvider();
    private ComponentProvider<Component, SectionNavigationElement> drawerSectionElementProvider = new DefaultLeftSectionElementComponentProvider();
    private ComponentProvider<Component, ClickableNavigationElement> drawerClickableElementProvider = new DefaultLeftClickableNavigationElementProvider();
    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> topNavigationElementProvider = new DefaultTopNavigationBadgeElementComponentProvider();
    private ComponentProvider<Component, SectionNavigationElement> topSectionElementProvider = new DefaultTopSectionElementComponentProvider();
    private ComponentProvider<Component, SubmenuNavigationElement> topSubmenuElementProvider = new DefaultTopSubmenuNavigationElementProvider();
    private ComponentProvider<Component, ClickableNavigationElement> topClickableElementProvider = new DefaultTopClickableNavigationElementProvider();

    public LeftFallBack(Behaviour variant) {
        this.setSpacing(false);
        this.setMargin(false);
        setSizeFull();

        addStyleNames("app-layout-behaviour-" + getStyleName(), "app-layout");
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
            menuHolder.addStyleNames("behaviour-content", "drawer-content");
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
            menuHolder.addStyleNames("behaviour-content", "drawer-content");
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

    @Override
    public void setNavigatorNavigationElements(List<NavigatorNavigationElement> list) {
        this.list = list;
    }

    @Override
    public void refreshNavigationElementInfo() {
        if (list != null) {
            list.forEach(element -> element.refreshInfo());
        }
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

    @Override
    public void addNavigationElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToDrawer(component.getComponent());
    }

    @Override
    public ComponentProvider<Component, SectionNavigationElement> getDrawerSectionElementProvider() {
        return drawerSectionElementProvider;
    }

    @Override
    public void setDrawerSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        drawerSectionElementProvider = provider;
    }

    @Override
    public ComponentProvider<Component, SectionNavigationElement> getTopSectionElementProvider() {
        return topSectionElementProvider;
    }

    @Override
    public void setTopSectionElementProvider(ComponentProvider<Component, SectionNavigationElement> provider) {
        topSectionElementProvider = provider;
    }

    @Override
    public ComponentProvider<Component, SubmenuNavigationElement> getDrawerSubmenuElementProvider() {
        return drawerSubmenuElementProvider;
    }

    @Override
    public void setDrawerSubmenuElementProvider(ComponentProvider<Component, SubmenuNavigationElement> provider) {
        drawerSubmenuElementProvider = provider;
    }

    @Override
    public ComponentProvider<Component, SubmenuNavigationElement> getTopSubmenuElementProvider() {
        return topSubmenuElementProvider;
    }

    @Override
    public void setTopSubmenuElementProvider(ComponentProvider<Component, SubmenuNavigationElement> provider) {
        topSubmenuElementProvider = provider;
    }

    @Override
    public ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> getDrawerNavigationElementProvider() {
        return drawerNavigationElementProvider;
    }

    @Override
    public void setDrawerNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        drawerNavigationElementProvider = provider;
    }

    @Override
    public ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> getTopNavigationElementProvider() {
        return topNavigationElementProvider;
    }

    @Override
    public void setTopNavigationElementProvider(ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> provider) {
        topNavigationElementProvider = provider;
    }

    @Override
    public ComponentProvider<Component, ClickableNavigationElement> getTopClickableElementProvider() {
        return topClickableElementProvider;
    }

    @Override
    public void setTopClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> topClickableElementProvider) {
        this.topClickableElementProvider = topClickableElementProvider;
    }

    @Override
    public ComponentProvider<Component, ClickableNavigationElement> getDrawerClickableElementProvider() {
        return drawerClickableElementProvider;
    }

    @Override
    public void setDrawerClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> drawerClickableElementProvider) {
        this.drawerClickableElementProvider = drawerClickableElementProvider;
    }

    @Override
    public void addNavigationFooterElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToDrawerFooter(component.getComponent());
    }

    @Override
    public void addNavigationHeaderElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToDrawerHeader(component.getComponent());
    }


    @Override
    public void addToDrawer(Component component) {
        menuElementHolder.addComponent(component);
    }

    @Override
    public void addToDrawerFooter(Component component) {
        menuFooterHolder.setVisible(true);
        menuFooterHolder.addComponent(component);
    }

    @Override
    public void addToDrawerHeader(Component component) {
        menuHeaderHolder.setVisible(true);
        menuHeaderHolder.addComponent(component);
    }

    @Override
    public void addToTop(Component component) {
        throw new UnsupportedOperationException("The Left Layout does not support this operation");
    }

    @Override
    public void addToTopFooter(Component component) {
        throw new UnsupportedOperationException("The Left Layout does not support this operation");
    }

    @Override
    public void addToTopHeader(Component component) {
        throw new UnsupportedOperationException("The Left Layout does not support this operation");
    }
}
