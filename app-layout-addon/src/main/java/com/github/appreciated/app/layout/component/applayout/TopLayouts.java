package com.github.appreciated.app.layout.component.applayout;

import com.github.appreciated.app.layout.webcomponents.applayout.AppDrawer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;

import java.util.Arrays;

public interface TopLayouts {

    @Tag(Top.TAG)
    @JsModule("./com/github/appreciated/app-layout/top/top.js")
    class Top extends AppLayout {
        private static final long serialVersionUID = 1L;

        public final static String TAG = "app-layout-top";

        private final HorizontalLayout paperTabWrapper = new HorizontalLayout();
        private final Div appBarElements;
        private final Div contentHolder;
        private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
        private final VerticalLayout contentPanel = new VerticalLayout();
        private final HorizontalLayout appBar = new HorizontalLayout();
        private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
        private final FlexLayout titleWrapper = new FlexLayout();
        @Id("content")
        Div contentWrapper;
        private HasElement content;
        @Id("toggle")
        private Button menuButton;
        private Component title;
        private boolean upNavigationEnabled;

        public Top() {
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
            contentHolder = new Div();
            contentHolder.setHeight("100%");
            contentHolder.setWidth("100%");
            contentHolder.getElement().setAttribute("slot", "application-content");

            appBarElements.add(appBar);
            appBarElementWrapper.setSpacing(false);
            appBarElementWrapper.add(appBarElementContainer);
            appBarElementContainer.setHeight("100%");
            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            titleWrapper.setHeight("100%");
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
            menuButton.setIcon(VaadinIcon.ARROW_LEFT.create());
            menuButton.addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
            getElement().appendChild(appBarElements.getElement(), contentHolder.getElement());
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
            this.contentHolder.getElement().removeAllChildren();
            if (content != null) {
                this.contentHolder.getElement().appendChild(content.getElement());
                this.content = content;
            }
        }


        public Div getAppBarElements() {
            return appBarElements;
        }

        public HorizontalLayout getAppBar() {
            return appBar;
        }

        @Override
        public void setAppBar(Component component) {
            appBarElementContainer.removeAll();
            appBarElementContainer.add(component);
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

        public FlexLayout getTitleWrapper() {
            return titleWrapper;
        }

        public void setIconComponent(Component appBarIconComponent) {
            titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        @Override
        public void showUpNavigation(boolean visible) {
            menuButton.addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
            menuButton.getClassNames().set("show-back-arrow", visible);
            menuButton.setIcon(visible ? VaadinIcon.ARROW_LEFT.create() : VaadinIcon.MENU.create());
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
        public void setAppMenu(Component container) {
            paperTabWrapper.removeAll();
            paperTabWrapper.add(container);
        }

        @Override
        public void setPercentageHeight(boolean set) {
            if (set) {
                contentWrapper.getStyle().set("height", "100vh");
            } else {
                contentWrapper.getStyle().remove("height");
            }
        }

        @Override
        public Component getContentElement() {
            return (Component) content;
        }

        /*@Override
        public void configurePage(InitialPageSettings settings) {
            settings.addInlineWithContents("body {overflow-x: hidden !important;}", InitialPageSettings.WrapMode.STYLESHEET);
        }*/
    }

    @Tag(TopLarge.TAG)
    @JsModule("./com/github/appreciated/app-layout/top/top-large.js")
    class TopLarge extends AppLayout {
        private static final long serialVersionUID = 1L;

        public final static String TAG = "app-layout-top-large";

        private final HorizontalLayout appBarElementWrapper = new HorizontalLayout();
        private final VerticalLayout contentPanel = new VerticalLayout();
        private final HorizontalLayout paperTabWrapper = new HorizontalLayout();
        private final VerticalLayout appBarWrapper = new VerticalLayout();
        private final HorizontalLayout appBar = new HorizontalLayout();
        private final HorizontalLayout appBarElementContainer = new HorizontalLayout();
        private final Div appBarElements;
        private final Div contentElement;
        private final FlexLayout titleWrapper = new FlexLayout();
        @Id("content")
        Div contentWrapper;

        @Id("toggle")
        private Button menuButton;

        @Id("app-bar-elements")
        private Div appBarContent;

        private Component title;
        private HasElement content;
        private boolean upNavigationEnabled;

        public TopLarge() {
            contentPanel.setSizeFull();

            paperTabWrapper.getElement().getStyle()
                    .set("flex-grow", "1")
                    .set("flex-shrink", "1")
                    .set("align-self", "flex-end");
            paperTabWrapper.setWidthFull();
            paperTabWrapper.setHeight("var(--app-layout-bar-height)");
            paperTabWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            getElement().getClassList().addAll(Arrays.asList("app-layout-behaviour-" + getStyleName(), "app-layout"));
            appBar.add(titleWrapper, appBarElementWrapper);
            appBar.setFlexGrow(1.0, titleWrapper);
            appBar.setWidthFull();
            appBar.setHeightFull();
            appBarWrapper.add(appBar, paperTabWrapper);
            appBarWrapper.setMargin(false);
            appBarWrapper.setPadding(false);
            appBarWrapper.setSpacing(false);
            appBarElements = new Div();
            appBarElements.setHeightFull();
            appBarElements.getElement().setAttribute("slot", "app-bar-content");
            contentElement = new Div();
            contentElement.setHeightFull();
            contentElement.setWidthFull();
            contentElement.getElement().setAttribute("slot", "application-content");
            appBarElements.add(appBarWrapper);
            appBarElementWrapper.setSpacing(false);
            appBarElementWrapper.add(appBarElementContainer);
            appBarElementContainer.setHeightFull();
            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            titleWrapper.setHeightFull();
            titleWrapper.setWidthFull();
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
            titleWrapper.setHeight("var(--app-layout-bar-height)");

            appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
            appBarElementWrapper.setHeight("var(--app-layout-bar-height)");
            menuButton.setIcon(VaadinIcon.ARROW_LEFT.create());
            menuButton.addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
            getElement().appendChild(appBarElements.getElement(), contentElement.getElement());
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
            this.contentElement.getElement().removeAllChildren();
            if (content != null) {
                this.contentElement.getElement().appendChild(content.getElement());
                this.content = content;
            }
        }


        public Div getAppBarElements() {
            return appBarElements;
        }

        public HorizontalLayout getAppBar() {
            return appBar;
        }

        @Override
        public void setAppBar(Component component) {
            appBarElementContainer.removeAll();
            appBarElementContainer.add(component);
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
            HorizontalLayout wrapper = new HorizontalLayout(component);
            wrapper.setWidthFull();
            titleWrapper.add(wrapper);
            this.title = component;
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        }

        public FlexLayout getTitleWrapper() {
            return titleWrapper;
        }

        public void setIconComponent(Component appBarIconComponent) {
            titleWrapper.getElement().insertChild(0, appBarIconComponent.getElement());
            titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
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
            appBarContent.getElement().getClassList().set("show-back-arrow", visible);
            menuButton.addThemeNames(ButtonVariant.LUMO_TERTIARY.getVariantName(), ButtonVariant.LUMO_ICON.getVariantName(), ButtonVariant.LUMO_LARGE.getVariantName());
            menuButton.getElement().getClassList().set("show-back-arrow", visible);
        }

        @Override
        public void setAppMenu(Component container) {
            paperTabWrapper.removeAll();
            paperTabWrapper.add(container);
        }

        @Override
        public HasElement getContentElement() {
            return content;
        }

        @Override
        public void setPercentageHeight(boolean set) {
            if (set) {
                contentWrapper.getStyle().set("height", "100vh");
            } else {
                contentWrapper.getStyle().remove("height");
            }
        }

       /* @Override
        public void configurePage(InitialPageSettings settings) {
            settings.addInlineWithContents("body {overflow-x: hidden !important;}", InitialPageSettings.WrapMode.STYLESHEET);
        }*/
    }
}
