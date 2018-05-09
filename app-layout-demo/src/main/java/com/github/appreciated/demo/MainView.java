package com.github.appreciated.demo;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;

import java.util.function.Consumer;

import static com.github.appreciated.app.layout.builder.Section.FOOTER;
import static com.github.appreciated.app.layout.builder.Section.HEADER;
import static com.github.appreciated.app.layout.builder.entities.DefaultNotification.Priority.MEDIUM;

/**
 * The main view contains a button and a template element.
 */

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setMargin(false);
        setPadding(false);
        setDrawerVariant(Behaviour.LEFT);
        setSizeFull();
        notifications.addNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));
    }

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private Thread currentThread;

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
        getUI().ifPresent(ui -> {
            badge.increase();
            notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description" + badge.getCount(), priority));
        });
    }

    private void setDrawerVariant(Behaviour variant) {
        removeAll();
        Component drawer = AppLayout.getDefaultBuilder(variant)
                .withTitle("App Layout")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDesign(AppLayoutDesign.MATERIAL)
                //.withNavigatorConsumer(navigator -> {/* Do someting with it */})
                .add(new MenuHeader("Version 2.0.0", "logo.png"), HEADER)
                .addClickable("Set Behaviour HEADER", VaadinIcons.COG.create(), clickEvent -> openModeSelector(variant), HEADER)
                .add("Home", VaadinIcons.HOME.create(), badge, new View1())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS.create())
                        .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS.create())
                                .add("Charts3", VaadinIcons.SPLINE_CHART.create(), View2.class)
                                .add("Contact3", VaadinIcons.CONNECT.create(), View3.class)
                                .add("More3", VaadinIcons.COG.create(), View4.class)
                                .build())
                        .add("Contact1", VaadinIcons.CONNECT.create(), View3.class)
                        .add("More1", VaadinIcons.COG.create(), View4.class)
                        .build())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS.create())
                        .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS.create())
                                .add("Charts4", VaadinIcons.SPLINE_CHART.create(), View2.class)
                                .add("Contact4", VaadinIcons.CONNECT.create(), View3.class)
                                .add("More4", VaadinIcons.COG.create(), View4.class)
                                .build())
                        .add("Contact2", VaadinIcons.CONNECT.create(), View3.class)
                        .add("More2", VaadinIcons.COG.create(), View4.class)
                        .build())
                .add("Menu", VaadinIcons.MENU.create(), View5.class)
                .addClickable("Set Behaviour FOOTER", VaadinIcons.COG.create(), clickEvent -> openModeSelector(variant), FOOTER)
                .build();
        //drawer.addStyleName("left");
        add(drawer);
        reloadNotifications();
    }

    private void openModeSelector(Behaviour variant) {
        new BehaviourSelector(variant, variant1 -> setDrawerVariant(variant1)).open();
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

    static abstract class AbstractView extends HorizontalLayout {
        public AbstractView() {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setSizeFull();
            Label label = new Label("< " + getViewName() + " >");
            layout.add(label);
            layout.setAlignItems(Alignment.CENTER);
            add(layout);
            setMargin(true);
            setSizeFull();
            getElement().getStyle().set("overflow", "auto");
        }

        abstract String getViewName();
    }

    class BehaviourSelector extends Dialog {
        public BehaviourSelector(Behaviour current, Consumer<Behaviour> consumer) {

            VerticalLayout layout = new VerticalLayout();
            add(layout);
            RadioButtonGroup<Behaviour> group = new RadioButtonGroup<>();
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
            group.setItems(current);
            layout.add(group);
            group.addValueChangeListener(singleSelectionEvent -> {
                consumer.accept(singleSelectionEvent.getValue());
                close();
            });
        }
    }


}
