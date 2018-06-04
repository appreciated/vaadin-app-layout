package com.github.appreciated.app.layout.behaviour.top;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopSectionElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.top.DefaultTopSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTabs;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Arrays;
import java.util.List;

/**
 * The {@link AbstractTopAppLayoutBase} is supposed to be the base of any {@link AppLayoutElementBase} with a "Top Behaviour".
 */

public abstract class AbstractTopAppLayoutBase extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase {

    @Id("toggle")
    PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    Div appBarElements;
    @Id("menu-elements")
    Div menuElements;
    @Id("content")
    Div content;

    private final VerticalLayout contentPanel = new VerticalLayout();

    private final HorizontalLayout menuHeaderHolder = new HorizontalLayout();
    private final HorizontalLayout menuElementHolder = new HorizontalLayout();
    private final HorizontalLayout menuFooterHolder = new HorizontalLayout();

    private final HorizontalLayout menuHolder = new HorizontalLayout(menuHeaderHolder, menuElementHolder, menuFooterHolder);

    private final PaperTabs paperTabs;

    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private Component title = new Span("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(new HorizontalLayout(title));
    private List<NavigatorNavigationElement> list;

    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> topNavigationElementProvider = new DefaultTopNavigationBadgeElementComponentFactory();
    private ComponentFactory<HasElement, SectionNavigationElement> topSectionElementProvider = new DefaultTopSectionElementComponentFactory();
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> topSubmenuElementProvider = new DefaultTopSubmenuNavigationElementFactory();
    private ComponentFactory<HasElement, ClickableNavigationElement> topClickableElementProvider = new DefaultTopClickableNavigationElementFactory();

    public AbstractTopAppLayoutBase() {
        contentPanel.setSizeFull();
        menuHolder.setSizeUndefined();
        menuHolder.setHeight("100%");
        menuHolder.setJustifyContentMode(JustifyContentMode.CENTER);

        menuHeaderHolder.setJustifyContentMode(JustifyContentMode.CENTER);
        menuElementHolder.setHeight("100%");
        menuElementHolder.setJustifyContentMode(JustifyContentMode.CENTER);
        menuElementHolder.setHeight("100%");
        menuFooterHolder.setJustifyContentMode(JustifyContentMode.CENTER);
        menuFooterHolder.setHeight("100%");

        paperTabs = new PaperTabs();
        menuElementHolder.add(paperTabs);

        menuHolder.getElement().setAttribute("overflow", "auto");
        menuHeaderHolder.setVisible(false);
        menuFooterHolder.setVisible(false);
        getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
        /**add(contentPanel, "content");
         addComponent(menuHolder, "menu-webcomponents");
         addComponent(appBar, "app-bar-webcomponents");*/
        appBar.add(titleWrapper, appBarElementWrapper);
        appBar.setFlexGrow(1.0, titleWrapper);
        appBar.setWidth("100%");
        appBar.setHeight("100%");
        appBarElements.getElement().appendChild(appBar.getElement());
        menuElements.setWidth("100%");
        menuElements.getElement().appendChild(menuHolder.getElement());
        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setAlignItems(Alignment.START);
        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(Alignment.CENTER);
        ((Span) this.title).getStyle().set("white-space", "nowrap");
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
    public AppDrawer getDrawer() {
        return null;
    }

    @Override
    public void setAppLayoutContent(HasElement content) {
        if (content != null) {
            this.content.getElement().appendChild(content.getElement());
        }
    }

    public Div getAppBarElements() {
        return appBarElements;
    }

    public abstract String getStyleName();

    @Override
    public void addAppBarElement(Component component) {
        appBarElementContainer.add(component);
        appBarElementContainer.setAlignItems(Alignment.CENTER);
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
        if (this.title instanceof HasText) {
            ((HasText) this.title).setText(title);
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


    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    public HorizontalLayout getMenuElementHolder() {
        return menuElementHolder;
    }

    public HorizontalLayout getMenuFooterHolder() {
        return menuFooterHolder;
    }

    public HorizontalLayout getMenuHeaderHolder() {
        return menuHeaderHolder;
    }

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.add(appBarIconComponent);
    }

    @Override
    public ComponentFactory<HasElement, SectionNavigationElement> getSectionElementProvider() {
        return topSectionElementProvider;
    }

    @Override
    public void setSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider) {
        topSectionElementProvider = provider;
    }

    @Override
    public ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> getSubmenuElementProvider() {
        return topSubmenuElementProvider;
    }

    @Override
    public void setSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider) {
        topSubmenuElementProvider = provider;
    }


    @Override
    public ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getNavigationElementProvider() {
        return topNavigationElementProvider;
    }

    @Override
    public void setNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider) {
        topNavigationElementProvider = provider;
    }


    @Override
    public ComponentFactory<HasElement, ClickableNavigationElement> getClickableElementProvider() {
        return topClickableElementProvider;
    }

    @Override
    public void setClickableElementProvider(ComponentFactory<HasElement, ClickableNavigationElement> drawerClickableElementProvider) {
        this.topClickableElementProvider = drawerClickableElementProvider;
    }

    @Override
    public void addNavigationElement(AbstractNavigationElement element) {
        element.setProvider(this);
        addToMenu((Component) element.getComponent());
    }

    @Override
    public void addNavigationFooterElement(AbstractNavigationElement element) {
        element.setProvider(this);
        addToDrawerFooter((Component) element.getComponent());
    }

    @Override
    public void addNavigationHeaderElement(AbstractNavigationElement element) {
        element.setProvider(this);
        addToDrawerHeader((Component) element.getComponent());
    }

    @Override
    public void addToMenu(Component component) {
        //menuElementHolder.add(component);
        paperTabs.add(component);
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

    @Override
    public void setBackNavigation(boolean visible) {
        paperIconButton.setIcon(visible ? "arrow-back" : "menu");
    }
}
