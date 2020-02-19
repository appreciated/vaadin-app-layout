package com.github.appreciated.app.layout.component.router;

import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.design.ThemeHelper;
import com.github.appreciated.app.layout.navigation.UpNavigationHelper;
import com.github.appreciated.app.layout.session.UIAttributes;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * Base class implementing router layout. Extending class is supposed to call {@link #init(AppLayout)} to initialize
 * the {@link AppLayout}. This can be extended directly if there is another parent layout defining a viewport.
 */
public class AppLayoutRouterLayoutBase<T extends AppLayout> extends Composite<Div> implements RouterLayout {

    private final ThemeHelper helper;
    private HasElement currentContent;
    private T layout;

    public AppLayoutRouterLayoutBase() {
        getContent().setSizeFull();
        getContent().getElement().getStyle().set("overflow", "auto");
        // Workaround to hopefully fix the issues with Springboot.
        helper = new ThemeHelper();
    }

    public static <V extends AppLayoutRouterLayoutBase> V getCurrent(Class<V> mainAppLayoutClass) {
        return (V) UIAttributes.get(AppLayoutRouterLayoutBase.class);
    }

    @Override
    protected Div initContent() {
        return new Div();
    }

    public void init(T layout) {
        setLayout(layout);
        if (currentContent != null) {
            showRouterLayoutContent(currentContent);
        }
    }

    public void setLayout(T layout) {
        getContent().removeAll();
        this.layout = layout;
        getContent().add(layout);
        UIAttributes.set(AppLayout.class, layout);
        UIAttributes.set(AppLayoutRouterLayoutBase.class, this);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        currentContent = content;
        if (layout == null) {
            throw new IllegalStateException("init has AppLayoutRouterLayoutBase::init has not yet been called");
        }
        layout.setAppLayoutContent(content);
        if (layout.isUpNavigationEnabled()) {
            setUpNavigation(content);
        }
        layout.setPercentageHeight(
                currentContent.getElement().getStyle().get("height") != null &&
                        currentContent.getElement().getStyle().get("height").endsWith("%")
        );
    }

    private void setUpNavigation(HasElement content) {
        if (content instanceof Component) {
            layout.showUpNavigation(UpNavigationHelper.routeHasUpNavigation(((Component) content).getClass()));
        } else {
            layout.showUpNavigation(false);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (getClass().getAnnotation(Theme.class) != null && getClass().getAnnotation(Theme.class).value() != Lumo.class) {
            attachEvent.getUI().getPage().addStyleSheet("./com/github/appreciated/app-layout/app-layout-styles-material.css");
        } else {
            attachEvent.getUI().getPage().addStyleSheet("./com/github/appreciated/app-layout/app-layout-styles-lumo.css");
        }
        getUI().ifPresent(ui -> ui.addAfterNavigationListener(event -> {
            closeDrawerIfNotPersistent();
        }));
    }

    public void closeDrawerIfNotPersistent() {
        layout.closeDrawerIfNotPersistent();
    }

    public void closeDrawer() {
        layout.closeDrawer();
    }

    public void toggleDrawer() {
        layout.toggleDrawer();
    }

    public void openDrawer() {
        layout.openDrawer();
    }

    public T getAppLayout() {
        return layout;
    }

    public final AppLayout createAppLayoutInstance() {
        return null;
    }
}
