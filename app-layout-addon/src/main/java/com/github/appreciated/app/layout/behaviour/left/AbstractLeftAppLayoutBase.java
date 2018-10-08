package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.AppLayoutElementBase;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.design.AppLayoutDesign;
import com.github.appreciated.app.layout.design.Styles;
import com.github.appreciated.app.layout.router.HasBackNavigation;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;

import java.util.Arrays;

/**
 * The {@link AbstractLeftAppLayoutBase} is the supposed to be the base of any {@link AppLayoutElementBase} with a "Left Behaviour".
 */

public abstract class AbstractLeftAppLayoutBase extends AppLayout {

    @Id("toggle")
    private PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    private Div appBarElements;
    @Id("menu-elements")
    private Div menuElements;
    @Id("content")
    private Div content;
    @Id("drawer")
    private AppDrawer drawer;

    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private final HorizontalLayout titleWrapper = new HorizontalLayout();
    private Component title;
    private NavigationElementContainer appMenuContainer;

    public AbstractLeftAppLayoutBase() {
        super();
        getStyle().set("width", "100%")
                .set("height", "100%");
        getClassNames().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), Styles.APP_LAYOUT));
        HorizontalLayout appBarContentHolder = new HorizontalLayout(titleWrapper, appBarElementWrapper);
        appBarContentHolder.setSizeFull();
        appBarElements.add(appBarContentHolder);

        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.setSizeFull();
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

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

    public void setDesign(AppLayoutDesign design) {
        this.getElement().getClassList().add(design.getStyleName());
    }

    public HorizontalLayout getAppBarElementWrapper() {
        return appBarElementWrapper;
    }

    public Component getTitleLabel() {
        return title;
    }

    public void setIconComponent(Component appBarIconComponent) {
        titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
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
    public void setAppBar(Component component) {
        appBarElementContainer.removeAll();
        appBarElementContainer.add(component);
    }

    @Override
    public void setAppMenu(NavigationElementContainer container) {
        menuElements.removeAll();
        menuElements.add(container.getComponent());
        appMenuContainer = container;
    }

    @Override
    public Component getTitleComponent() {
        return title;
    }

    public void setTitleComponent(Component component) {
        titleWrapper.replace(this.title, component);
        this.title = component;
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public HorizontalLayout getTitleWrapper() {
        return titleWrapper;
    }

    @Override
    public void setBackNavigation(boolean visible) {
        paperIconButton.setIcon(visible ? "arrow-back" : "menu");
    }

    @Override
    public boolean setActiveNavigationComponent(Class<? extends HasElement> element) {
        return appMenuContainer.setActiveNavigationComponent(element);
    }

    @Override
    public Class<? extends HasElement> getClosestNavigationElement(Class<? extends HasElement> element) {
        return appMenuContainer.getClosestNavigationElement(element);
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
