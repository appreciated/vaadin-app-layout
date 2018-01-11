package com.github.appreciated.demo;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.AppLayoutBuilder.Position.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
@Push(transport = Transport.WEBSOCKET_XHR)
public class DemoUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    public void init(VaadinRequest request) {
        setContent(AppLayoutBuilder.get(Behaviour.LEFT_RESPONSIVE_HYBRID)
                .withTitle("App Layout")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.MATERIAL)
                .withNavigatorConsumer(navigator -> {/* Do someting with it */})
                .add(new MenuHeader("Version 0.9.19", new ThemeResource("logo.png")), HEADER)
                .add("Home", VaadinIcons.HOME, badge, new View1())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                        .add("Contact", VaadinIcons.CONNECT, View3.class)
                        .add("More", VaadinIcons.COG, View4.class)
                        .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                .build());
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public class View1 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View2 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View3 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View4 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View5 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View6 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    static abstract class AbstractView extends HorizontalLayout implements View {
        public AbstractView() {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSizeFull();
            Label label = new Label("< " + getViewName() + " >");
            label.addStyleNames(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN);
            layout.addComponent(label);
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            Panel panel = new Panel(layout);
            panel.setSizeFull();
            addComponent(panel);
            setMargin(true);
            setSizeFull();
        }

        abstract String getViewName();
    }

}