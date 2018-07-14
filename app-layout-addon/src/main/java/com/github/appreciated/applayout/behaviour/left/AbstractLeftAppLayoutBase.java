package com.github.appreciated.applayout.behaviour.left;

import com.github.appreciated.applayout.behaviour.AppLayoutElementBase;
import com.github.appreciated.applayout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.github.appreciated.applayout.design.Styles;
import com.github.appreciated.applayout.router.HasBackNavigation;
import com.github.appreciated.applayout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.applayout.webcomponents.appmenu.AppMenu;
import com.github.appreciated.applayout.webcomponents.papericonbutton.PaperIconButton;
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

    public AbstractLeftAppLayoutBase() {
        super();
        getElement().getStyle().set("width", "100%")
                .set("height", "100%");
        menuElements.add(menuElementHolder);
        //menuHeaderHolder.setVisible(false);
        //menuFooterHolder.setVisible(false);
        getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), Styles.APP_LAYOUT));
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
