package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
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

import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * The {@link AbstractLeftAppLayoutBase} is the supposed to be the base of any {@link AppLayoutElementBase} with a "Left Behaviour".
 */

public abstract class AbstractLeftAppLayoutBase extends AppLayout {

    private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
    private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
    private final HorizontalLayout titleWrapper = new HorizontalLayout();
    private final Div menuElements;
    private final Div content;
    @Id("toggle")
    private PaperIconButton paperIconButton;
    @Id("app-bar-elements")
    private Div appBarElements;

    @Id("drawer")
    private AppDrawer drawer;
    private Component title;
    private NavigationElementContainer appMenuContainer;
    private boolean isMenuVisible = true;

    //prepare to delete
    //    private HasElement appLayoutContent;


    AbstractLeftAppLayoutBase() {
        getStyle().set("width", "100%")
                .set("height", "100%");
        getClassNames().addAll(asList("app-layout-behaviour-" + getStyleName(), Styles.APP_LAYOUT));
        HorizontalLayout appBarContentHolder = new HorizontalLayout(titleWrapper, appBarElementWrapper);
        appBarContentHolder.setSizeFull();
        appBarContentHolder.getElement().setAttribute("slot", "app-bar-content");

        appBarElementWrapper.setSpacing(false);
        appBarElementWrapper.setSizeFull();
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementContainer.setHeight("100%");
        appBarElementWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);


        menuElements = new Div();
        menuElements.getElement().setAttribute("slot", "drawer-content");
        content = new Div();
        content.setHeight("100%");
        content.setWidth("100%");
        content.getElement().setAttribute("slot", "application-content");

        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        titleWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        titleWrapper.setPadding(false);
        titleWrapper.setMargin(false);
        getElement().getClassList().add("app-layout");

        getElement().appendChild(appBarContentHolder.getElement(), menuElements.getElement(), content.getElement());
    }

    @Override
    public AppDrawer getDrawer() {
        return drawer;
    }

    public abstract String getStyleName();

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
        if (appMenuContainer != null) {
            return appMenuContainer.setActiveNavigationComponent(element);
        }
        return false;
    }

    @Override
    public Optional<Class<? extends HasElement>> getClosestNavigationElement(Class<? extends HasElement> element) {
        if (appMenuContainer != null) {
            return appMenuContainer.getClosestNavigationElement(element);
        }
        return Optional.empty();
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void init() {
        /**
         * if no menu elements were added hide the button and menu
         */
        if (appMenuContainer == null) {
            setMenuVisible(false);
        }
    }

    /**
     * Weather the menu is currently hidden or not.
     *
     * @return false of the menu is currently hidden.
     */
    public boolean isMenuVisible() {
        return isMenuVisible;
    }

    /**
     * Hiding the menu will hide all menu elements also on the client side.
     * </br> Note that you still have to make sure that an unauthorized user is unable to access the paths available in the menu!
     *
     * @param isMenuVisible
     */
    public void setMenuVisible(boolean isMenuVisible) {
        if (isMenuVisible != this.isMenuVisible) { // only do something if the state was changed
            this.isMenuVisible = isMenuVisible;
            if (isMenuVisible) {
                if (menuElements.getChildren().count() == 0 && appMenuContainer != null) { // if the container is empty add the component
                    menuElements.add(appMenuContainer.getComponent());
                }
                drawer.getElement().getStyle().remove("display");
                paperIconButton.getElement().getStyle().remove("display");
                getElement().getStyle().remove("--app-layout-drawer-width");
                getElement().getStyle().remove("--app-layout-drawer-small-width");
            } else {
                drawer.getElement().getStyle().set("display", "none");
                menuElements.removeAll();
                paperIconButton.getElement().getStyle().set("display", "none");
                getElement().getStyle().set("--app-layout-drawer-width", "0px");
                getElement().getStyle().set("--app-layout-drawer-small-width", "0px");
            }
        }
    }
}
