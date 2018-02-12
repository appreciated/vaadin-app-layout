package com.github.appreciated.demo;

import com.github.appreciated.app.layout.behaviour.AppLayoutComponent;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayout;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;
import java.util.function.Consumer;

import static com.github.appreciated.app.layout.builder.AppLayoutConfiguration.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.AppLayoutConfiguration.Position.HEADER;
import static com.github.appreciated.app.layout.builder.entities.DefaultNotification.Priority.MEDIUM;

@PushStateNavigation
@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
@Push(transport = Transport.WEBSOCKET_XHR)
public class DemoUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private VerticalLayout holder;
    private Thread currentThread;

    @Override
    protected void init(VaadinRequest request) {
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(Behaviour.LEFT_RESPONSIVE);
        setContent(holder);
        holder.setSizeFull();
        notifications.addNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));
    }

    private void reloadNotifications() {
        if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badge.clearCount();
        notifications.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    addNotification(MEDIUM);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();
    }

    private void addNotification(DefaultNotification.Priority priority) {
        DemoUI.this.access(() -> {
            badge.increase();
            notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description" + badge.getCount(), priority));
        });
    }

    private void setDrawerVariant(Behaviour variant) {
        holder.removeAllComponents();
        AppLayoutComponent drawer = AppLayout.getDefaultBuilder(variant)
                .withTitle("App Layout")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.MATERIAL)
                //.withNavigatorConsumer(navigator -> {/* Do someting with it */})
                .add(new MenuHeader("Version 0.9.21", new ThemeResource("logo.png")), HEADER)
                .addClickable("Set Behaviour HEADER", VaadinIcons.COG, clickEvent -> openModeSelector(variant), HEADER)
                .add("Home", VaadinIcons.HOME, badge, new View1())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                                .add("Charts3", VaadinIcons.SPLINE_CHART, View2.class)
                                .add("Contact3", VaadinIcons.CONNECT, View3.class)
                                .add("More3", VaadinIcons.COG, View4.class)
                                .build())
                        .add("Contact1", VaadinIcons.CONNECT, View3.class)
                        .add("More1", VaadinIcons.COG, View4.class)
                        .build())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                        .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                                .add("Charts4", VaadinIcons.SPLINE_CHART, View2.class)
                                .add("Contact4", VaadinIcons.CONNECT, View3.class)
                                .add("More4", VaadinIcons.COG, View4.class)
                                .build())
                        .add("Contact2", VaadinIcons.CONNECT, View3.class)
                        .add("More2", VaadinIcons.COG, View4.class)
                        .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                .addClickable("Set Behaviour FOOTER", VaadinIcons.COG, clickEvent -> openModeSelector(variant), FOOTER)
                .build();
        drawer.addStyleName("left");
        holder.addComponent(drawer);
        if (getNavigator() != null) {
            getNavigator().navigateTo("");
        }
        reloadNotifications();
    }

    private void openModeSelector(Behaviour variant) {
        UI.getCurrent().addWindow(new BehaviourSelector(variant, variant1 -> setDrawerVariant(variant1)));
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
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

    public static class View7 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
    }

    public static class View8 extends AbstractView {
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

    class BehaviourSelector extends Window {
        public BehaviourSelector(Behaviour current, Consumer<Behaviour> consumer) {
            setModal(true);
            setClosable(true);
            setCaption("Select Behaviour");
            VerticalLayout layout = new VerticalLayout();
            setContent(layout);
            RadioButtonGroup<Behaviour> group = new RadioButtonGroup<>();
            group.addStyleName(ValoTheme.OPTIONGROUP_LARGE);
            group.setItems(Behaviour.LEFT,
                    Behaviour.LEFT_OVERLAY,
                    Behaviour.LEFT_RESPONSIVE,
                    Behaviour.LEFT_HYBRID,
                    Behaviour.LEFT_HYBRID_SMALL,
                    Behaviour.LEFT_RESPONSIVE_HYBRID,
                    Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR,
                    Behaviour.LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR,
                    Behaviour.LEFT_RESPONSIVE_OVERLAY,
                    Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
                    Behaviour.LEFT_RESPONSIVE_SMALL,
                    Behaviour.LEFT_RESPONSIVE_SMALL_NO_APP_BAR,
                    Behaviour.TOP,
                    Behaviour.TOP_LARGE);
            group.setSelectedItem(current);
            layout.addComponent(group);
            group.addSelectionListener(singleSelectionEvent -> {
                consumer.accept(singleSelectionEvent.getSelectedItem().orElse(Behaviour.LEFT_RESPONSIVE));
                close();
            });
        }
    }

}