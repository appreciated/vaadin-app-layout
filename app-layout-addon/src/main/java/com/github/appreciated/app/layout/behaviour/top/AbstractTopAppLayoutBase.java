package com.github.appreciated.app.layout.behaviour.top;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSectionElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopSectionElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Arrays;
import java.util.List;

import static com.github.appreciated.app.layout.behaviour.Position.DRAWER;
import static com.github.appreciated.app.layout.behaviour.Position.TOP;


/**
 * The {@link AbstractTopAppLayoutBase} is supposed to be the base of any {@link AppLayoutElementBase} with a "Top Behaviour".
 */

public abstract class AbstractTopAppLayoutBase extends Div implements AppLayoutElementBase {

    private final VerticalLayout contentPanel = new VerticalLayout();

    private final VerticalLayout menuHeaderHolder = new VerticalLayout();
    private final VerticalLayout menuElementHolder = new VerticalLayout();
    private final VerticalLayout menuFooterHolder = new VerticalLayout();

    private final VerticalLayout menuHolder = new VerticalLayout(menuHeaderHolder, menuElementHolder, menuFooterHolder);

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private Component title = new Label("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(new HorizontalLayout(title));
    private List<NavigatorNavigationElement> list;

    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> drawerNavigationElementProvider = new DefaultLeftNavigationBadgeElementComponentFactory();
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> drawerSubmenuElementProvider = new DefaultLeftSubmenuNavigationElementFactory();
    private ComponentFactory<HasElement, SectionNavigationElement> drawerSectionElementProvider = new DefaultLeftSectionElementComponentFactory();
    private ComponentFactory<HasElement, ClickableNavigationElement> drawerClickableElementProvider = new DefaultLeftClickableNavigationElementFactory();
    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> topNavigationElementProvider = new DefaultTopNavigationBadgeElementComponentFactory();
    private ComponentFactory<HasElement, SectionNavigationElement> topSectionElementProvider = new DefaultTopSectionElementComponentFactory();
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> topSubmenuElementProvider = new DefaultTopSubmenuNavigationElementFactory();
    private ComponentFactory<HasElement, ClickableNavigationElement> topClickableElementProvider = new DefaultTopClickableNavigationElementFactory();

    public AbstractTopAppLayoutBase() {
        contentPanel.setSizeFull();
        menuHolder.setSizeFull();
        menuHolder.setFlexGrow(1.0, menuElementHolder);
        menuHolder.getElement().setAttribute("overflow", "auto");
        menuHeaderHolder.setVisible(false);
        menuFooterHolder.setVisible(false);
        menuHeaderHolder.setMargin(false);
        menuElementHolder.setMargin(true);
        menuFooterHolder.setMargin(true);
        menuElementHolder.setWidth("100%");
        getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
        /**add(contentPanel, "content");
         addComponent(menuHolder, "menu-webcomponents");
         addComponent(appBar, "app-bar-webcomponents");*/
        appBar.add(titleWrapper, appBarElementWrapper);
        appBar.setFlexGrow(1.0, titleWrapper);
        appBar.setWidth("100%");
        appBar.setHeight("100%");
        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
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

    @Override
    public void setAppLayoutContent(HasElement content) {

    }

    public abstract String getStyleName();

    @Override
    public void addAppBarElement(Component component) {
        appBarElementContainer.add(component);
        appBarElementContainer.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public void setDesign(AppLayoutDesign design) {
        this.getElement().getClassList().add(design.getStyleName());
    }

    public HorizontalLayout getAppBar() {
        return appBar;
    }

    public HorizontalLayout getAppBarElementWrapper() {
        return appBarElementWrapper;
    }

    public Component getTitleLabel() {
        return title;
    }

    public void setTitle(String title) {
        if (this.title instanceof Label) {
            ((Label) this.title).setText(title);
        }
    }

    @Override
    public void setTitleElement(HasElement titleComponent) {

    }

    @Override
    public Component getTitleComponent() {
        return title;
    }

    public void setTitleComponent(Component component) {
        titleWrapper.replace(this.title, component);
        this.title = component;
    }


    public HasComponents getContentHolder() {
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

    public VerticalLayout getMenuHolder() {
        return menuHolder;
    }

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.add(appBarIconComponent);
    }

    @Override
    public ComponentFactory<HasElement, SectionNavigationElement> getDrawerSectionElementProvider() {
        return drawerSectionElementProvider;
    }

    @Override
    public void setDrawerSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider) {
        drawerSectionElementProvider = provider;
    }

    @Override
    public ComponentFactory<HasElement, SectionNavigationElement> getTopSectionElementProvider() {
        return topSectionElementProvider;
    }

    @Override
    public void setTopSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider) {
        topSectionElementProvider = provider;
    }

    @Override
    public ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> getDrawerSubmenuElementProvider() {
        return drawerSubmenuElementProvider;
    }

    @Override
    public void setDrawerSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider) {
        drawerSubmenuElementProvider = provider;
    }

    @Override
    public ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> getTopSubmenuElementProvider() {
        return topSubmenuElementProvider;
    }

    @Override
    public void setTopSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider) {
        topSubmenuElementProvider = provider;
    }

    @Override
    public ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getDrawerNavigationElementProvider() {
        return drawerNavigationElementProvider;
    }

    @Override
    public void setDrawerNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider) {
        drawerNavigationElementProvider = provider;
    }

    @Override
    public ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getTopNavigationElementProvider() {
        return topNavigationElementProvider;
    }

    @Override
    public void setTopNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider) {
        topNavigationElementProvider = provider;
    }

    @Override
    public ComponentFactory<HasElement, ClickableNavigationElement> getTopClickableElementProvider() {
        return topClickableElementProvider;
    }

    @Override
    public void setTopClickableElementProvider(ComponentFactory<HasElement, ClickableNavigationElement> topClickableElementProvider) {
        this.topClickableElementProvider = topClickableElementProvider;
    }

    @Override
    public ComponentFactory<HasElement, ClickableNavigationElement> getDrawerClickableElementProvider() {
        return drawerClickableElementProvider;
    }

    @Override
    public void setDrawerClickableElementProvider(ComponentFactory<HasElement, ClickableNavigationElement> drawerClickableElementProvider) {
        this.drawerClickableElementProvider = drawerClickableElementProvider;
    }

    @Override
    public void addNavigationElement(AbstractNavigationElement element) {
        element.setProvider(this, DRAWER);
        addToDrawer((Component) element.getComponent());
        if (!(element instanceof ComponentNavigationElement)) { // Components cannot be added twice
            element.setProvider(this, TOP);
            addToTop((Component) element.getComponent());
        }
    }

    @Override
    public void addNavigationFooterElement(AbstractNavigationElement element) {
        element.setProvider(this, DRAWER);
        addToDrawerFooter((Component) element.getComponent());
        if (!(element instanceof ComponentNavigationElement)) { // Components cannot be added twice
            element.setProvider(this, TOP);
            addToTopFooter((Component) element.getComponent());
        }
    }

    @Override
    public void addNavigationHeaderElement(AbstractNavigationElement element) {
        element.setProvider(this, TOP);
        addToDrawerHeader((Component) element.getComponent());
        if (!(element instanceof ComponentNavigationElement)) { // Components cannot be added twice
            element.setProvider(this, TOP);
            addToTopHeader((Component) element.getComponent());
        }
    }

    @Override
    public void addToDrawer(Component component) {
        menuElementHolder.add(component);
    }

    @Override
    public void addToDrawerFooter(Component component) {
        menuFooterHolder.setVisible(true);
        menuFooterHolder.add(component);
    }

    @Override
    public void addToDrawerHeader(Component component) {
        menuHeaderHolder.setVisible(true);
        menuHeaderHolder.add(component);
    }
}
