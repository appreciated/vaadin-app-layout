package com.github.appreciated.app.layout.router;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public abstract class AppLayoutRouterLayout extends Composite<Div> implements RouterLayout {

    public static final String SESSION_ATTRIBUTE_APP_LAYOUT = "app-layout-instance";

    private HasElement currentContent;
    private AppLayout layout;

    public AppLayoutRouterLayout() {
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

    public final void createAppLayoutInstance() {
    }
}
