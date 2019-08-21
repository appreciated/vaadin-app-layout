package com.github.appreciated.app.layout.component.router;

import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.navigation.UpNavigationHelper;
import com.github.appreciated.app.layout.session.UIAttributes;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;

/**
 * Base class implementing router layout. Extending class is supposed to call {@link #init(AppLayout)} to initialize
 * the {@link AppLayout}. This can be extended directly if there is another parent layout defining a viewport.
 */
@CssImport("./com/github/appreciated/app-layout/app-layout-styles-lumo.css")
public class AppLayoutRouterLayoutBase<T extends AppLayout> extends Composite<Div> implements RouterLayout {

    private HasElement currentContent;
    private T layout;

    public AppLayoutRouterLayoutBase() {
        getContent().setSizeFull();
        getContent().getElement().getStyle().set("overflow", "auto");
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
        getUI().ifPresent(ui -> ui.addAfterNavigationListener(event -> {
            closeDrawerIfNotPersistent();
        }));
    }

    public static <T extends AppLayoutRouterLayoutBase> T getAppLayoutRouterLayout() {
        return (T) UIAttributes.get(AppLayoutRouterLayoutBase.class);
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
