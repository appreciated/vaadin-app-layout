package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.router.navigation.UpNavigationHelper;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

/**
 * Base class implementing router layout. Extending class is supposed to call {@link #init(AppLayout)} to initialize
 * the {@link AppLayout}. This can be extended directly if there is another parent layout defining a viewport.
 */
@HtmlImport("frontend://src/com/github/appreciated/app-layout/app-layout-styles.html")
public class AppLayoutRouterLayoutBase extends Composite<Div> implements RouterLayout {

    public static final String SESSION_ATTRIBUTE_APP_LAYOUT = "app-layout-instance";

    private HasElement currentContent;
    private AppLayout layout;

    public AppLayoutRouterLayoutBase() {
        getContent().setSizeFull();
        getContent().getElement().getStyle().set("overflow", "auto");
    }

    public static AppLayout getCurrent() {
        return (AppLayout) UI.getCurrent().getSession().getAttribute(SESSION_ATTRIBUTE_APP_LAYOUT);
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
        UI.getCurrent().getSession().setAttribute(SESSION_ATTRIBUTE_APP_LAYOUT, layout);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        currentContent = content;
        layout.setAppLayoutContent(content);
        if (content.getClass().getAnnotation(Route.class) != null) {
            boolean has = layout.hasNavigationElement(content.getClass());
            layout.setBackNavigation(!has);
            if (!layout.setActiveNavigationElement(content.getClass())) {
                layout.getClosestNavigationElement(content.getClass())
                        .ifPresent(aClass -> layout.setActiveNavigationElement(aClass));
            }
        }
        setBackNavigation(content);
    }

    private void setBackNavigation(HasElement content) {
        if (content instanceof Component) {
            layout.setBackNavigation(UpNavigationHelper.routeHasUpNavigation(((Component) content).getClass()));
        } else {
            layout.setBackNavigation(false);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> ui.addAfterNavigationListener(afterNavigationEvent -> {
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
