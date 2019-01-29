package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@StyleSheet("frontend://com/github/appreciated/app-layout/app-layout.css")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout extends Composite<Div> implements RouterLayout {

    public static final String SESSION_ATTRIBUTE_APP_LAYOUT = "app-layout-instance";

    private AppLayout appLayout;
    private HasElement currentContent;

    public AppLayoutRouterLayout() {
        appLayout = createAppLayoutInstance();
        setLayoutToSession();
        getContent().add(appLayout);
        getContent().setSizeFull();
        getContent().getElement().getStyle().set("overflow", "auto");
    }

    public abstract AppLayout createAppLayoutInstance();

    public void setLayoutToSession() {
        UI.getCurrent().getSession().setAttribute(SESSION_ATTRIBUTE_APP_LAYOUT, appLayout);
    }

    public static AppLayout getCurrent() {
        return (AppLayout) UI.getCurrent().getSession().getAttribute(SESSION_ATTRIBUTE_APP_LAYOUT);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> ui.addAfterNavigationListener(afterNavigationEvent -> {
            closeDrawerIfNotPersistent();
        }));
    }

    public void closeDrawerIfNotPersistent() {
        appLayout.closeDrawerIfNotPersistent();
    }

    public void reloadConfiguration() {
        getContent().removeAll();
        appLayout = createAppLayoutInstance();
        setLayoutToSession();
        getContent().add(appLayout);
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
                appLayout
                        .getClosestNavigationElement(content.getClass())
                        .ifPresent(aClass -> appLayout.setActiveNavigationComponent(aClass));
            }
        }
    }

    public void closeDrawer() {
        appLayout.closeDrawer();
    }

    public void toggleDrawer() {
        appLayout.toggleDrawer();
    }

    public void openDrawer() {
        appLayout.openDrawer();
    }

    public AppLayout getAppLayout() {
        return appLayout;
    }
}
