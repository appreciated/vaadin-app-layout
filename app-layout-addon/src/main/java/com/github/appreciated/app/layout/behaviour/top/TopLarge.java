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
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.papertabs.PaperTabs;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

@Tag("app-layout-top-large")
@HtmlImport("frontend://bower_components/vaadin-icons/vaadin-icons.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer/app-drawer.html")
@HtmlImport("frontend://bower_components/app-layout/app-drawer-layout/app-drawer-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-header/app-header.html")
@HtmlImport("frontend://bower_components/app-layout/app-header-layout/app-header-layout.html")
@HtmlImport("frontend://bower_components/app-layout/app-toolbar/app-toolbar.html")
@HtmlImport("frontend://bower_components/app-layout/app-scroll-effects/effects/waterfall.html")
@HtmlImport("frontend://com/github/appreciated/app-layout/top/top-large.html")
public class TopLarge extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase {
    protected final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final VerticalLayout contentPanel = new VerticalLayout();
    private final PaperTabs paperTabs = new PaperTabs();
    private final HorizontalLayout paperTabWrapper = new HorizontalLayout(paperTabs);
    private final VerticalLayout appBarWrapper = new VerticalLayout();
    private final HorizontalLayout appBar = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    //@Id("toggle")
    // PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    Div appBarElements;
    @Id("content")
    Div content;
    private Component title = new Span("");
    protected final HorizontalLayout titleWrapper = new HorizontalLayout(new HorizontalLayout(title));
    private List<NavigatorNavigationElement> list;
    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> topNavigationElementProvider = new DefaultTopNavigationBadgeElementComponentFactory();
    private ComponentFactory<HasElement, SectionNavigationElement> topSectionElementProvider = new DefaultTopSectionElementComponentFactory();
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> topSubmenuElementProvider = new DefaultTopSubmenuNavigationElementFactory();
    private ComponentFactory<HasElement, ClickableNavigationElement> topClickableElementProvider = new DefaultTopClickableNavigationElementFactory();

    public TopLarge() {
        contentPanel.setSizeFull();
        paperTabs.setHeight("100%");
        //paperTabs.getElement().getStyle().set("align-items", "center");
        paperTabWrapper.getElement().getStyle()
                .set("flex-grow", "1")
                .set("flex-shrink", "1")
                .set("align-self", "flex-end");
        paperTabWrapper.setWidth("100%");
        paperTabWrapper.setHeight("var(--app-bar-height)");
        paperTabWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
        /**add(contentPanel, "content");
         addComponent(menuHolder, "menu-webcomponents");
         addComponent(appBar, "app-bar-webcomponents");*/
        appBar.add(titleWrapper, appBarElementWrapper);
        appBar.setFlexGrow(1.0, titleWrapper);
        appBar.setWidth("100%");
        appBar.setHeight("100%");
        appBarWrapper.add(appBar, paperTabWrapper);
        appBarWrapper.setMargin(false);
        appBarWrapper.setPadding(false);
        appBarWrapper.setSpacing(false);
        appBarElements.add(appBarWrapper);
        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        ((Span) this.title).getStyle().set("white-space", "nowrap");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        titleWrapper.setHeight("var(--app-bar-height)");

        appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
        appBarElementWrapper.setHeight("var(--app-bar-height)");
    }

    @Override
    public String getStyleName() {
        return "top";
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

    @Override
    public HasComponents getMenuElementHolder() {
        return paperTabs;
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
        paperTabs.add(component);
    }

    @Override
    public void addToDrawerFooter(Component component) {
        paperTabs.add(component);
    }

    @Override
    public void addToDrawerHeader(Component component) {
        paperTabs.add(component);
    }

    @Override
    public void setBackNavigation(boolean visible) {
        //paperIconButton.setIcon(visible ? "arrow-back" : "menu");
    }

    @Override
    public void setActiveElement(HasElement content) {
        paperTabs.getChildren()
                .filter(item -> item instanceof NavigationElementContainer)
                .map(item -> (NavigationElementContainer) item)
                .forEach(navigationBadgeIconButton -> navigationBadgeIconButton.setActiveNavigationElementWithViewClass(content));
    }
}

