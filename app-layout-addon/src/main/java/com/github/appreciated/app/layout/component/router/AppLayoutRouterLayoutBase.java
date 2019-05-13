package com.github.appreciated.app.layout.component.router;

import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.navigation.UpNavigationHelper;
import com.github.appreciated.app.layout.session.UIAttributes;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLayout;

/**
 * Base class implementing router layout. Extending class is supposed to call {@link #init(AppLayout)} to initialize
 * the {@link AppLayout}. This can be extended directly if there is another parent layout defining a viewport.
 */
@HtmlImport("frontend://src/com/github/appreciated/app-layout/app-layout-styles.html")
public class AppLayoutRouterLayoutBase extends Composite<Div> implements RouterLayout {

    private HasElement currentContent;
    private AppLayout layout;

    public AppLayoutRouterLayoutBase() {
        getContent().setSizeFull();
        getContent().getElement().getStyle().set("overflow", "auto");
    }

    public static AppLayout getCurrent() {
        return UIAttributes.get(AppLayout.class);
    }

    public void init(AppLayout layout) {
        setLayout(layout);
        if (currentContent != null) {
            showRouterLayoutContent(currentContent);
        }
    }

    public void setLayout(AppLayout layout) {
        getContent().removeAll();
        this.layout = layout;
        getContent().add(layout);
        UIAttributes.set(AppLayout.class, layout);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        currentContent = content;
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

    public AppLayout getAppLayout() {
        return layout;
    }

    public final AppLayout createAppLayoutInstance() {
        return null;
    }
}
