package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.*;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftClickableNavigationElementFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftNavigationBadgeElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSectionElementComponentFactory;
import com.github.appreciated.app.layout.builder.factories.left.DefaultLeftSubmenuNavigationElementFactory;
import com.github.appreciated.app.layout.builder.interfaces.ComponentFactory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.router.HasBackNavigation;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.appmenu.AppMenu;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Arrays;
import java.util.List;

import static com.github.appreciated.app.layout.builder.design.Styles.APP_LAYOUT;

/**
 * The {@link AbstractLeftAppLayoutBase} is the supposed to be the base of any {@link AppLayoutElementBase} with a "Left Behaviour".
 */

public abstract class AbstractLeftAppLayoutBase extends PolymerTemplate<TemplateModel> implements AppLayoutElementBase {

    @Id("toggle")
    PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    Div appBarElements;
    @Id("menu-elements")
    Div menuElements;
    @Id("content")
    Div content;
    @Id("drawer")
    AppDrawer drawer;

    private final AppMenu menuElementHolder = new AppMenu();

    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private Component title = new Span("");
    private final HorizontalLayout titleWrapper = new HorizontalLayout(title);
    private List<NavigatorNavigationElement> list;

    private ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> navigationElementProvider = new DefaultLeftNavigationBadgeElementComponentFactory();
    private ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> submenuElementProvider = new DefaultLeftSubmenuNavigationElementFactory();
    private ComponentFactory<HasElement, SectionNavigationElement> sectionElementProvider = new DefaultLeftSectionElementComponentFactory();
    private ComponentFactory<HasElement, ClickableNavigationElement> clickableElementProvider = new DefaultLeftClickableNavigationElementFactory();

    public AbstractLeftAppLayoutBase() {
        super();
        getElement().getStyle().set("width", "100%")
                .set("height", "100%");
        menuElements.add(menuElementHolder);
        //menuHeaderHolder.setVisible(false);
        //menuFooterHolder.setVisible(false);
        getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), APP_LAYOUT));
        HorizontalLayout appBarContentHolder = new HorizontalLayout(titleWrapper, appBarElementWrapper);
        appBarContentHolder.setSizeFull();
        appBarElements.add(appBarContentHolder);

        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.setSizeFull();
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        ((Span) this.title).getStyle().set("white-space", "nowrap");

        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        titleWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        titleWrapper.setPadding(false);
        titleWrapper.setMargin(false);
        getElement().getClassList().add("app-layout");
    }

    @Override
    public AppDrawer getDrawer() {
        return drawer;
    }

    public abstract String getStyleName();

    @Override
    public void setNavigatorNavigationElements(List<NavigatorNavigationElement> list) {
        this.list = list;
    }

    @Override
    public void refreshNavigationElementInfo() {
        if (list != null) {
            list.forEach(NavigatorNavigationElement::refreshInfo);
        }
    }

    public void setDesign(AppLayoutDesign design) {
        this.getElement().getClassList().add(design.getStyleName());
    }

    public HorizontalLayout getAppBarElementWrapper() {
        return appBarElementWrapper;
    }

    public Component getTitleLabel() {
        return title;
    }

    @Override
    public void addAppBarElement(Component component) {
        appBarElementContainer.add(component);
        appBarElementContainer.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    @Override
    public void setTitle(String title) {
        if (this.title instanceof HasText) {
            ((HasText) this.title).setText(title);
        }
    }

    @Override
    public void setTitleElement(HasElement titleComponent) {
        if (this.title instanceof HasText) {
            ((HasText) this.title).setText("test1234");
        }
    }

    @Override
    public void setAppLayoutContent(HasElement content) {
        if (content != null) {
            this.content.getElement().appendChild(content.getElement());
            setUpBackNavigation(content);
        }
    }

    private void setUpBackNavigation(HasElement content) {
        if (content instanceof HasBackNavigation) {
            paperIconButton.setIcon("back");
        } else {
            paperIconButton.setIcon("menu");
        }
    }

    @Override
    public Component getTitleComponent() {
        return title;
    }

    public void setTitleComponent(Component compoent) {
        titleWrapper.replace(this.title, compoent);
        this.title = compoent;
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    public AppMenu getMenuElementHolder() {
        return menuElementHolder;
    }

    public void addAppBarIcon(Component appBarIconComponent) {
        titleWrapper.add(appBarIconComponent);
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }


    @Override
    public ComponentFactory<HasElement, SectionNavigationElement> getSectionElementProvider() {
        return sectionElementProvider;
    }

    @Override
    public void setSectionElementProvider(ComponentFactory<HasElement, SectionNavigationElement> provider) {
        sectionElementProvider = provider;
    }


    @Override
    public ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> getSubmenuElementProvider() {
        return submenuElementProvider;
    }

    @Override
    public void setSubmenuElementProvider(ComponentFactory<SubmenuNavigationElement.SubmenuElement, SubmenuNavigationElement> provider) {
        submenuElementProvider = provider;
    }

    @Override
    public ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> getNavigationElementProvider() {
        return navigationElementProvider;
    }

    @Override
    public void setNavigationElementProvider(ComponentFactory<NavigationElementComponent, NavigatorNavigationElement> provider) {
        navigationElementProvider = provider;
    }

    @Override
    public ComponentFactory<HasElement, ClickableNavigationElement> getClickableElementProvider() {
        return clickableElementProvider;
    }

    @Override
    public void setClickableElementProvider(ComponentFactory<HasElement, ClickableNavigationElement> drawerClickableElementProvider) {
        this.clickableElementProvider = drawerClickableElementProvider;
    }

    @Override
    public void addNavigationElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToMenu((Component) component.getComponent());
    }

    @Override
    public void addNavigationFooterElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToDrawerFooter((Component) component.getComponent());
    }

    @Override
    public void addNavigationHeaderElement(AbstractNavigationElement component) {
        component.setProvider(this);
        addToDrawerHeader((Component) component.getComponent());
    }

    @Override
    public void addToMenu(Component component) {
        menuElementHolder.add(component);
    }

    @Override
    public void addToDrawerFooter(Component component) {
        menuElementHolder.add(component);
    }

    @Override
    public void addToDrawerHeader(Component component) {
        menuElementHolder.add(component);
    }

    @Override
    public void setBackNavigation(boolean visible) {
        paperIconButton.setIcon(visible ? "arrow-back" : "menu");
    }

    @Override
    public void setActiveElement(HasElement content) {
        getMenuElementHolder().getChildren()
                .filter(item -> item instanceof NavigationElementContainer)
                .map(item -> (NavigationElementContainer) item)
                .forEach(navigationBadgeIconButton -> navigationBadgeIconButton.setActiveNavigationElementWithViewClass(content));
    }
}
