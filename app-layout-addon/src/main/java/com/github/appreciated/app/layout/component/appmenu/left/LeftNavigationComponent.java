package com.github.appreciated.app.layout.component.appmenu.left;

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.builder.AppLayoutConfiguration;
import com.github.appreciated.app.layout.builder.interfaces.Factory;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementComponent;
import com.github.appreciated.app.layout.component.appmenu.NavigationBadgeIconButton;
import com.github.appreciated.app.layout.entity.NavigationElementInfo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;

/**
 * A wrapper class for a MenuElement that is clickable and backed by the Navigator. Which means that
 * clicks on instances on {@link LeftNavigationComponent} respectively their
 * {@link com.vaadin.flow.component.Component} which will usually causes a change of the View at the
 * AppLayout content view.
 */
public class LeftNavigationComponent extends NavigationBadgeIconButton
    implements NavigationElementComponent {
  /**
   * The caption of this menu element
   */
  private String caption;
  /**
   * The respective view behind this menu element (either {@link LeftNavigationComponent#view} or
   * {@link LeftNavigationComponent#className} will be initialized)
   */
  private Component view;
  /**
   * The view behind this menu element
   */
  private Icon icon;
  /**
   * The respective view behind this menu element (either {@link LeftNavigationComponent#view} or
   * {@link LeftNavigationComponent#className} will be initialized)
   */
  private Class<? extends Component> className;

  /**
   * The view name interceptor that allows replace the route before initializing the router cannot
   * be used when using cdi This can come in handy in some situations where the route does not
   * conform a proper URL-encoding Note: May be null
   */
  private Factory<String, String> routeInterceptor;
  /**
   * The
   * {@link com.github.appreciated.app.layout.builder.AppLayoutConfiguration.NavigationElementInfoProducer}
   * instance which will eventually later on be used to provide the caption, route and icon for this
   * menu element for the View / view class. Note: May be null
   */
  private AppLayoutConfiguration.NavigationElementInfoProducer navigationElementInfoProvider;
  private NavigationElementInfo info;

  /**
   * The route interceptor that allows replace the caption of each menu element that has one before
   * initializing. This can f.e. be used to replace I18N string with their localized string value
   * Note: May be null
   */
  private Factory<String, String> captionInterceptor;

  public LeftNavigationComponent(String caption, Icon icon, Component view) {
    super(caption, icon);
    this.caption = caption;
    this.icon = icon;
    this.view = view;
    setClickListener(appMenuIconItemClickEvent -> navigateTo());
  }

  public LeftNavigationComponent(String caption, VaadinIcon icon,
      Class<? extends Component> className) {
    super(caption, icon.create());
    this.caption = caption;
    this.icon = icon.create();
    this.className = className;
    setClickListener(appMenuIconItemClickEvent -> navigateTo());
  }

  public LeftNavigationComponent(String caption, Icon icon, Class<? extends Component> className) {
    super(caption, icon);
    this.caption = caption;
    this.icon = icon;
    this.className = className;
    setClickListener(appMenuIconItemClickEvent -> navigateTo());
  }

  public LeftNavigationComponent(Component view) {
    super();
    this.view = view;
    setText(className.getAnnotation(Caption.class).value());
    setIcon(className.getAnnotation(com.github.appreciated.app.layout.annotations.Icon.class)
        .value().create());
    setClickListener(appMenuIconItemClickEvent -> navigateTo());
  }

  public LeftNavigationComponent(Class<? extends Component> className) {
    super();
    this.className = className;
    setText(className.getAnnotation(Caption.class).value());
    setIcon(className.getAnnotation(com.github.appreciated.app.layout.annotations.Icon.class)
        .value().create());
    setClickListener(appMenuIconItemClickEvent -> navigateTo());
  }

  public void navigateTo() {
    UI.getCurrent().navigate(getRoute());
  }

  public String getRoute() {
    if (className != null) {
      return className.getAnnotation(Route.class).value();
    } else if (view != null) {
      return view.getClass().getAnnotation(Route.class).value();
    } else {
      return getCaption();
    }
  }

  private String getCaption() {
    if (caption != null) {
      return caption;
    } else if (info != null) {
      return info.getCaption();
    }
    return null;
  }

  public Icon getIcon() {
    if (icon != null) {
      return icon;
    } else if (info != null) {
      return info.getIcon();
    }
    return null;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
    this.setIcon(icon.getElement().getAttribute("icon"));
  }

  public Component getView() {
    return view;
  }

  public Class<? extends Component> getViewClassName() {
    if (className == null) {
      return view == null ? null : view.getClass();
    } else {
      return className;
    }
  }

  @Override
  public boolean setActiveNavigationComponent(Class<? extends HasElement> element) {
    if (getViewClassName() == element) {
      setActive();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Class<? extends Component> getNavigationElement() {
    return getViewClassName();
  }
}
