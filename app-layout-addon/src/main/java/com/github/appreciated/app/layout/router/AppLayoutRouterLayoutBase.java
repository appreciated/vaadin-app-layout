package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.util.Comparator;
import java.util.Optional;

/**
 * Base class implementing router layout. Extending class is supposed to call {@link #init(AppLayout)} to initialize
 * the {@link AppLayout}. This can be extended directly if there is another parent layout defining a viewport.
 */
@StyleSheet("frontend://com/github/appreciated/app-layout/app-layout-lumo.css")
@StyleSheet("frontend://com/github/appreciated/app-layout/app-layout-material.css")
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
            String currentRoute = UI.getCurrent().getRouter().getUrl(((Component) content).getClass());
            if (currentRoute.lastIndexOf("/") > 0) {
                System.out.println(currentRoute);
                String[] currentRouteParts = currentRoute.substring(0, (currentRoute.lastIndexOf("/"))).split("/");
                Optional<RouteDataSimilarity> result = UI.getCurrent().getRouter().getRoutes()
                        .stream()
                        .filter(routeData -> !routeData.getUrl().equals(currentRoute))
                        .map(routeData -> new RouteDataSimilarity(routeData, currentRouteParts))
                        .max(Comparator.comparingInt(RouteDataSimilarity::getSimilarity));
                result.ifPresent(parentRoute -> System.out.println(parentRoute.getSimilarity()));
                layout.setBackNavigation(result.isPresent());
            } else {
                layout.setBackNavigation(false);
            }
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
