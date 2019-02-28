package com.github.appreciated.app.layout.behaviour;

import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.github.appreciated.app.layout.webcomponents.papericonbutton.PaperIconButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;

import java.util.Arrays;

public interface TopLayouts {

    @Tag("app-layout-top")
    @HtmlImport("frontend://com/github/appreciated/app-layout/top/top.html")
    class Top extends AppLayout {
        private final HorizontalLayout paperTabWrapper = new HorizontalLayout();
        private final Div appBarElements;
        private final Div content;
        private NavigationElementContainer navigationElementContainer;

        @Override
        public String getStyleName() {
            return "top";
        }

        private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
        private final VerticalLayout contentPanel = new VerticalLayout();
        private final HorizontalLayout appBar = new HorizontalLayout();
        private final HorizontalLayout appBarElementContainer = new HorizontalLayout();

        @Id("toggle")
        private PaperIconButton paperIconButton;
        private final HorizontalLayout titleWrapper = new HorizontalLayout();
        private Component title;

        Top() {
            contentPanel.setSizeFull();
            getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
            appBar.add(titleWrapper, paperTabWrapper, appBarElementWrapper);
            paperTabWrapper.setFlexGrow(1.0, titleWrapper);
            paperTabWrapper.setWidth("100%");
            paperTabWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            appBar.setWidth("100%");
            appBar.setHeight("100%");

            appBarElements = new Div();
            appBarElements.setHeight("100%");
            appBarElements.getElement().setAttribute("slot", "app-bar-content");
            content = new Div();
            content.setHeight("100%");
            content.setWidth("100%");
            content.getElement().setAttribute("slot", "application-content");

            appBarElements.add(appBar);
            appBarElementWrapper.setSpacing(false);
            appBarElementWrapper.add(appBarElementContainer);
            appBarElementContainer.setHeight("100%");
            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            titleWrapper.setHeight("100%");
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);

            getElement().appendChild(appBarElements.getElement(), content.getElement());
        }

        @Override
        public AppDrawer getDrawer() {
            return null;
        }

        @Override
        public void setAppLayoutContent(HasElement content) {
            this.content.getElement().removeAllChildren();
            if (content != null) {
                this.content.getElement().appendChild(content.getElement());
            }
        }

        public Div getAppBarElements() {
            return appBarElements;
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
        public Component getTitleComponent() {
            return title;
        }

        public void setTitleComponent(Component component) {
            titleWrapper.add(new HorizontalLayout(component));
            this.title = component;
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        public HorizontalLayout getTitleWrapper() {
            return titleWrapper;
        }


        public void setIconComponent(Component appBarIconComponent) {
            titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        @Override
        public void setBackNavigation(boolean visible) {
            paperIconButton.setIcon(visible ? "arrow-back" : "menu");
        }

        @Override
        public boolean setActiveNavigationElement(Class<? extends HasElement> element) {
            return navigationElementContainer.setActiveNavigationElement(element);
        }

        @Override
        public Component getComponent() {
            return this;
        }

        @Override
        public void setAppBar(Component component) {
            appBarElementContainer.removeAll();
            appBarElementContainer.add(component);
        }

        @Override
        public void setAppMenu(NavigationElementContainer container) {
            paperTabWrapper.removeAll();
            paperTabWrapper.add(container.getComponent());
            navigationElementContainer = container;
        }
    }

    @Tag("app-layout-top-large")
    @HtmlImport("frontend://com/github/appreciated/app-layout/top/top-large.html")
    class TopLarge extends AppLayout {
        private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
        private final VerticalLayout contentPanel = new VerticalLayout();
        private final HorizontalLayout paperTabWrapper = new HorizontalLayout();
        private final VerticalLayout appBarWrapper = new VerticalLayout();
        private final HorizontalLayout appBar = new HorizontalLayout();
        private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
        private final Div appBarElements;
        private final Div content;

        @Id("toggle")
        private PaperIconButton paperIconButton;
        private final HorizontalLayout titleWrapper = new HorizontalLayout();
        private Component title;
        private NavigationElementContainer appMenuContainer;

        TopLarge() {
            contentPanel.setSizeFull();

            paperTabWrapper.getElement().getStyle()
                    .set("flex-grow", "1")
                    .set("flex-shrink", "1")
                    .set("align-self", "flex-end");
            paperTabWrapper.setWidth("100%");
            paperTabWrapper.setHeight("var(--app-layout-bar-height)");
            paperTabWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
            appBar.add(titleWrapper, appBarElementWrapper);
            appBar.setFlexGrow(1.0, titleWrapper);
            appBar.setWidth("100%");
            appBar.setHeight("100%");
            appBarWrapper.add(appBar, paperTabWrapper);
            appBarWrapper.setMargin(false);
            appBarWrapper.setPadding(false);
            appBarWrapper.setSpacing(false);
            appBarElements = new Div();
            appBarElements.setHeight("100%");
            appBarElements.getElement().setAttribute("slot", "app-bar-content");
            content = new Div();
            content.setHeight("100%");
            content.setWidth("100%");
            content.getElement().setAttribute("slot", "application-content");
            appBarElements.add(appBarWrapper);
            appBarElementWrapper.setSpacing(false);
            appBarElementWrapper.add(appBarElementContainer);
            appBarElementContainer.setHeight("100%");
            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            titleWrapper.setHeight("100%");
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
            titleWrapper.setHeight("var(--app-layout-bar-height)");

            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            appBarElementWrapper.setHeight("var(--app-layout-bar-height)");
            paperIconButton.setIcon("arrow-back");

            getElement().appendChild(appBarElements.getElement(), content.getElement());
        }

        @Override
        public String getStyleName() {
            return "top";
        }

        @Override
        public AppDrawer getDrawer() {
            return null;
        }

        @Override
        public void setAppLayoutContent(HasElement content) {
            this.content.getElement().removeAllChildren();
            if (content != null) {
                this.content.getElement().appendChild(content.getElement());
            }
        }

        public Div getAppBarElements() {
            return appBarElements;
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
        public Component getTitleComponent() {
            return title;
        }

        public void setTitleComponent(Component component) {
            titleWrapper.add(new HorizontalLayout(component));
            this.title = component;
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        public HorizontalLayout getTitleWrapper() {
            return titleWrapper;
        }

        public void setIconComponent(Component appBarIconComponent) {
            titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        @Override
        public void setBackNavigation(boolean visible) {
            appBarElements.getElement().getClassList().set("show-back-arrow", visible);
            paperIconButton.getElement().getClassList().set("show-back-arrow", visible);
        }

        @Override
        public boolean setActiveNavigationElement(Class<? extends HasElement> element) {
            return appMenuContainer.setActiveNavigationElement(element);
        }

        @Override
        public void setAppBar(Component component) {
            appBarElementContainer.removeAll();
            appBarElementContainer.add(component);
        }

        @Override
        public void setAppMenu(NavigationElementContainer container) {
            paperTabWrapper.removeAll();
            paperTabWrapper.add(container.getComponent());
            appMenuContainer = container;
        }

        @Override
        public Component getComponent() {
            return this;
        }
    }
}
