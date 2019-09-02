package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.design.Styles;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.polymertemplate.Id;

import static java.util.Arrays.asList;

/**
 * The {@link AbstractLeftAppLayoutBase} is the supposed to be the base of any {@link AppLayoutElementBase} with a "Left Behaviour".
 */
public abstract class AbstractLeftAppLayoutBase extends AppLayout {

    private final FlexLayout appBarElementWrapper = new FlexLayout();
    private final FlexLayout appBarElementContainer = new FlexLayout();
    private final FlexLayout titleWrapper = new FlexLayout();
    private final Div menuElements;
    private final Div contentHolder;
    @Id("content")
    Div contentWrapper;
    @Id("toggle")
    private Button menuButton;
    @Id("app-bar-elements")
    private Div appBarElements;
    @Id("drawer")
    private AppDrawer drawer;
    private Component title;
    private boolean isMenuVisible = true;
    private HasElement content;
    private Component container;
    private boolean upNavigationEnabled = false;

    AbstractLeftAppLayoutBase() {
        getClassNames().addAll(asList("app-layout-behaviour-" + getStyleName(), Styles.APP_LAYOUT));
        FlexLayout appBarContentHolder = new FlexLayout(titleWrapper, appBarElementWrapper);
        appBarContentHolder.setSizeFull();
        appBarContentHolder.getElement().setAttribute("slot", "app-bar-content");

        appBarElementWrapper.getStyle().set("flex", "0 1 auto");
        appBarElementWrapper.add(appBarElementContainer);
        appBarElementWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        appBarElementWrapper.getStyle().set("flex-direction", "var(--app-layout-app-bar-flex-direction)");
        appBarElementContainer.getStyle().set("flex-direction", "var(--app-layout-app-bar-flex-direction)");
        titleWrapper.getStyle().set("flex-direction", "var(--app-layout-app-bar-flex-direction)");

        menuElements = new Div();
        menuElements.setHeight("100%");
        menuElements.getElement().setAttribute("slot", "drawer-content");
        contentHolder = new Div();
        contentHolder.setHeight("100%");
        contentHolder.setWidth("100%");
        contentHolder.getElement().setAttribute("slot", "application-content");

        titleWrapper.setHeight("100%");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);

        titleWrapper.getElement().getStyle().set("flex", "1 1").set("overflow", "hidden");
        getElement().getClassList().add("app-layout");

        getElement().appendChild(appBarContentHolder.getElement(), menuElements.getElement(), contentHolder.getElement());
    }

    public abstract String getStyleName();

    @Override
    public AppDrawer getDrawer() {
        return drawer;
    }

    public FlexLayout getAppBarElementWrapper() {
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
        this.contentHolder.getElement().removeAllChildren();
        if (content != null) {
            this.contentHolder.getElement().appendChild(content.getElement());
            this.content = content;
        }
    }

    @Override
    public void setAppBar(Component component) {
        appBarElementContainer.removeAll();
        appBarElementContainer.add(component);
    }

    @Override
    public void setAppMenu(Component container) {
        this.container = container;
        menuElements.removeAll();
        menuElements.add(container);
    }

    @Override
    public Component getTitleComponent() {
        return title;
    }

    public void setTitleComponent(Component component) {
        titleWrapper.replace(this.title, component);
        this.title = component;
        this.title.getElement().getStyle().set("display", "var(--app-layout-app-bar-large-object-display)");
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
    }

    public FlexLayout getTitleWrapper() {
        return titleWrapper;
    }

    @Override
    public boolean isUpNavigationEnabled() {
        return upNavigationEnabled;
    }

    @Override
    public void setUpNavigationEnabled(boolean enable) {
        this.upNavigationEnabled = enable;
    }

    @Override
    public void showUpNavigation(boolean visible) {
        menuButton.addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
        menuButton.getClassNames().set("show-back-arrow", visible);
        menuButton.getElement().removeAllChildren();
        menuButton.setIcon(visible ? VaadinIcon.ARROW_LEFT.create() : VaadinIcon.MENU.create());
    }

    @Override
    public HasElement getContentElement() {
        return content;
    }


    @Override
    public void init() {
        /**
         * if no menu elements were added hide the button and menu
         */
        if (container == null) {
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
     * Note that you still have to make sure that an unauthorized user is unable to access the paths available in the menu!
     *
     * @param isMenuVisible whether the menu is visible
     */
    public void setMenuVisible(boolean isMenuVisible) {
        if (isMenuVisible != this.isMenuVisible) { // only do something if the state was changed
            this.isMenuVisible = isMenuVisible;
            if (isMenuVisible) {
                if (menuElements.getChildren().count() == 0 && container != null) { // if the container is empty add the component
                    menuElements.add(container);
                }
                drawer.getElement().getStyle().remove("display");
                menuButton.getElement().getStyle().remove("display");
                getElement().getStyle().remove("--app-layout-drawer-width");
                getElement().getStyle().remove("--app-layout-drawer-small-width");
            } else {
                drawer.getElement().getStyle().set("display", "none");
                menuElements.removeAll();
                menuButton.getElement().getStyle().set("display", "none");
                getElement().getStyle().set("--app-layout-drawer-width", "0px");
                getElement().getStyle().set("--app-layout-drawer-small-width", "0px");
            }
        }
    }

    @Override
    public void setPercentageHeight(boolean set) {
        if (set) {
            contentWrapper.getStyle().set("height", "100vh");
        } else {
            contentWrapper.getStyle().remove("height");
        }
    }

}
