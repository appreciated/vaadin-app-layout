package com.github.appreciated.app.layout.router;

import javax.annotation.PostConstruct;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
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

  private AppLayout appLayout;
  private HasElement currentContent;

  public AppLayoutRouterLayout() {
    getContent().setSizeFull();
    getContent().getElement().getStyle().set("overflow" , "auto");
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    super.onAttach(attachEvent);
    getUI().ifPresent(ui -> ui.addAfterNavigationListener(afterNavigationEvent -> {
      closeDrawerIfNotPersistent();
    }));
  }

  public void reloadConfiguration() {
    getContent().removeAll();
    loadConfiguration();
    if (currentContent != null) {
      showRouterLayoutContent(currentContent);
    }
  }

  @Override
  public void showRouterLayoutContent(HasElement content) {
    currentContent = content;
    getInternalAppLayout().setAppLayoutContent(content);
    if (content.getClass().getAnnotation(Route.class) != null) {
      int value = content.getClass().getAnnotation(Route.class).value().split("/").length;
      getInternalAppLayout().setBackNavigation(value > 1);
      if (! getInternalAppLayout().setActiveNavigationComponent(content.getClass())) {
        getInternalAppLayout()
            .getClosestNavigationElement(content.getClass())
            .ifPresent(aClass -> getInternalAppLayout().setActiveNavigationComponent(aClass));
      }
    }
  }

  @PostConstruct
  private void loadConfiguration() {
    appLayout = getAppLayout();
    getContent().add((Component) appLayout);
  }

  public static AppLayoutRouterLayout getCurrent() {
    return (AppLayoutRouterLayout) UI.getCurrent().getSession().getAttribute("app-layout");
  }

  public abstract AppLayout getAppLayout();

  public void closeDrawer() {
    getInternalAppLayout().closeDrawer();
  }

  public void toggleDrawer() {
    getInternalAppLayout().toggleDrawer();
  }

  public void openDrawer() {
    getInternalAppLayout().openDrawer();
  }

  public void closeDrawerIfNotPersistent() {
    getInternalAppLayout().closeDrawerIfNotPersistent();
  }

  private AppLayout getInternalAppLayout() {
    if (appLayout == null)
      loadConfiguration();
    return appLayout;
  }
}
