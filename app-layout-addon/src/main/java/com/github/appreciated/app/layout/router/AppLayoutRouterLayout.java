package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import javax.annotation.PostConstruct;

@StyleSheet("frontend://com/github/appreciated/app-layout/app-layout.css")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout extends Div implements RouterLayout {

    private AppLayout appLayout;
    private HasElement currentContent;

    public AppLayoutRouterLayout() {
        setSizeFull();
        getElement().getStyle().set("overflow", "auto");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> ui.addAfterNavigationListener(afterNavigationEvent -> {
            closeDrawerIfNotPersistent();
        }));
    }

    public void reloadConfiguration() {
        removeAll();
        loadConfiguration();
        if (currentContent != null) {
            showRouterLayoutContent(currentContent);
        }
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        currentContent = content;
        appLayout.setAppLayoutContent(content);
        if (content.getClass().getAnnotation(Route.class) != null) {
            int value = content.getClass().getAnnotation(Route.class).value().split("/").length;
            appLayout.setBackNavigation(value > 1);
            if (!appLayout.setActiveNavigationComponent(content.getClass())) {
                Class<? extends HasElement> closestElement = appLayout.getClosestNavigationElement(content.getClass());
                appLayout.setActiveNavigationComponent(closestElement);
            }
        }
    }

    @PostConstruct
    private void loadConfiguration() {
        appLayout = getAppLayout();
        add((Component) appLayout);
    }

    public static AppLayoutRouterLayout getCurrent() {
        return (AppLayoutRouterLayout) UI.getCurrent().getSession().getAttribute("app-layout");
    }

    public abstract AppLayout getAppLayout();

    public void closeDrawer() {
        appLayout.closeDrawer();
    }

    public void toggleDrawer() {
        appLayout.toggleDrawer();
    }

    public void openDrawer() {
        appLayout.openDrawer();
    }

    public void closeDrawerIfNotPersistent() {
        appLayout.closeDrawerIfNotPersistent();
    }

}
