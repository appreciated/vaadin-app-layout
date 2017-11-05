package com.github.appreciated.app.layout.behaviour.top;

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
import com.github.appreciated.app.layout.component.HorizontalFlexBoxLayout;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import static com.github.appreciated.app.layout.behaviour.AppLayout.Position.TOP;

public class TopFallBack extends VerticalLayout implements AppLayout {

    private final Panel contentPanel = new Panel();

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();

    private final HorizontalFlexBoxLayout appBarHeaderMenuContainer = new HorizontalFlexBoxLayout();
    private final HorizontalFlexBoxLayout appBarMenuContainer = new HorizontalFlexBoxLayout();
    private final HorizontalFlexBoxLayout appBarFooterMenuContainer = new HorizontalFlexBoxLayout();
    private final HorizontalFlexBoxLayout appBarMenuContainerWrapper = new HorizontalFlexBoxLayout(appBarHeaderMenuContainer, appBarMenuContainer, appBarFooterMenuContainer);
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title, appBarMenuContainerWrapper);

    private final Label title = new Label("");
    VerticalLayout wrapper = new VerticalLayout(appBar, contentPanel);
    private List<NavigatorNavigationElement> list;

    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> drawerNavigationElementProvider = new DefaultLeftNavigationBadgeElementComponentProvider();
    private ComponentProvider<Component, SubmenuNavigationElement> drawerSubmenuElementProvider = new DefaultLeftSubmenuNavigationElementProvider();
    private ComponentProvider<Component, SectionNavigationElement> drawerSectionElementProvider = new DefaultLeftSectionElementComponentProvider();
    private ComponentProvider<Component, ClickableNavigationElement> drawerClickableElementProvider = new DefaultLeftClickableNavigationElementProvider();
    private ComponentProvider<NavigationElementComponent, NavigatorNavigationElement> topNavigationElementProvider = new DefaultTopNavigationBadgeElementComponentProvider();
    private ComponentProvider<Component, SectionNavigationElement> topSectionElementProvider = new DefaultTopSectionElementComponentProvider();
    private ComponentProvider<Component, SubmenuNavigationElement> topSubmenuElementProvider = new DefaultTopSubmenuNavigationElementProvider();
    private ComponentProvider<Component, ClickableNavigationElement> topClickableElementProvider = new DefaultTopClickableNavigationElementProvider();

    public TopFallBack(Behaviour variant) {
        addStyleNames("app-layout-behaviour-" + getStyleName(), "app-layout");
        this.setSpacing(false);
        this.setMargin(false);
        setSizeFull();
        contentPanel.setSizeFull();

        appBarMenuContainerWrapper.setHeight(100, Unit.PERCENTAGE);
        appBarMenuContainerWrapper.setAlignCenter();
        appBarHeaderMenuContainer.setAlignCenter();
        appBarMenuContainer.setAlignCenter();
        appBarFooterMenuContainer.setAlignCenter();

        contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        wrapper.setExpandRatio(contentPanel, 1);
        addStyleName(getStyleName());
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
        addComponent(wrapper);
        wrapper.setSizeFull();
        wrapper.setMargin(false);
        wrapper.setSpacing(false);
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

    public String getStyleName() {
        return "left-fallback";
    }


    public void addNavigationElement(Component component) {
        appBarMenuContainer.addComponent(component);
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
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    public VerticalLayout getMenuFooterHolder() {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    public VerticalLayout getMenuHeaderHolder() {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    public VerticalLayout getMenuHolder() {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.addComponentAsFirst(appBarIconComponent);
        titleWrapper.setComponentAlignment(appBarIconComponent, Alignment.MIDDLE_LEFT);
    }

    @Override
    public void addNavigationElement(AbstractNavigationElement element) {
        element.setProvider(this, TOP);
        addToTop(element.getComponent());
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
    public ComponentProvider<Component, ClickableNavigationElement> getDrawerClickableElementProvider() {
        return drawerClickableElementProvider;
    }

    @Override
    public void setDrawerClickableElementProvider(ComponentProvider<Component, ClickableNavigationElement> drawerClickableElementProvider) {
        this.drawerClickableElementProvider = drawerClickableElementProvider;
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
    public void addNavigationFooterElement(AbstractNavigationElement element) {
        element.setProvider(this, TOP);
        addToTopFooter(element.getComponent());
    }

    @Override
    public void addNavigationHeaderElement(AbstractNavigationElement element) {
        element.setProvider(this, TOP);
        addToTopHeader(element.getComponent());
    }

    @Override
    public void addToDrawer(Component component) {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    @Override
    public void addToDrawerFooter(Component component) {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    @Override
    public void addToDrawerHeader(Component component) {
        throw new UnsupportedOperationException("The TopFallBack Layout does not support this operation");
    }

    @Override
    public void addToTop(Component component) {
        addNavigationElement(component);
    }

    @Override
    public void addToTopFooter(Component component) {
        appBarFooterMenuContainer.addComponent(component);
    }

    @Override
    public void addToTopHeader(Component component) {
        appBarHeaderMenuContainer.addComponent(component);
    }
}
