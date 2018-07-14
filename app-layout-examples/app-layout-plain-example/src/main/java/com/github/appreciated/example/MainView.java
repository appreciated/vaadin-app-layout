package com.github.appreciated.example;

import com.github.appreciated.applayout.behaviour.Behaviour;
import com.github.appreciated.applayout.builder.AbstractAppLayoutBuilderBase;
import com.github.appreciated.applayout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.applayout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.applayout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.applayout.design.AppLayoutDesign;
import com.github.appreciated.applayout.entity.DefaultBadgeHolder;
import com.github.appreciated.applayout.notification.DefaultNotificationHolder;
import com.github.appreciated.applayout.notification.component.AppBarNotificationButton;
import com.github.appreciated.applayout.notification.entitiy.DefaultNotification;
import com.github.appreciated.applayout.notification.entitiy.Priority;
import com.github.appreciated.applayout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

import java.util.function.Consumer;

import static com.github.appreciated.applayout.entity.Section.FOOTER;
import static com.github.appreciated.applayout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainView extends AppLayoutRouterLayout {
    DefaultNotificationHolder notifications;
    DefaultBadgeHolder badge;
    private Behaviour variant;
    private Thread currentThread;

    @Override
    public AbstractAppLayoutBuilderBase getAppLayoutElementBase() {
        if (variant == null) {
            variant = Behaviour.LEFT;
            notifications = new DefaultNotificationHolder();
            badge = new DefaultBadgeHolder();
        }
        reloadNotifications();

        if (!variant.isTop()) {
            return AppLayoutBuilder.get(variant)
                    .withTitle("App Layout")
                    .withAppBar(
                            AppBarBuilder
            new AppBarNotificationButton(VaadinIcon.BELL.create(), notifications))
                    .withDesign(AppLayoutDesign.MATERIAL)
                    .withMenu(
                            LeftAppMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                    .add(new MenuHeaderComponent("App-Layout", "Version 2.0.0", "frontend/images/logo.png"), HEADER)
                                    .add(new LeftClickableComponent("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant))HEADER)
                                    .add(LeftAppMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                            .add("Charts", VaadinIcon.SPLINE_CHART.create(), View2.class)
                                            .add("Contact", VaadinIcon.CONNECT.create(), View3.class)
                                            .add("More", VaadinIcon.COG.create(), View4.class)
                                            .build())
                                    .add("Contact1", VaadinIcon.CONNECT.create(), View3.class)
                                    .add("More1", VaadinIcon.COG.create(), View4.class)
                                    .build())
                    .add(LeftAppMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                            .add(LeftAppMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                    .add("Charts4", VaadinIcon.SPLINE_CHART.create(), View2.class)
                                    .add("Contact4", VaadinIcon.CONNECT.create(), View3.class)
                                    .add("More4", VaadinIcon.COG.create(), View4.class)
                                    .build())
                            .add("Contact2", VaadinIcon.CONNECT.create(), View3.class)
                            .add("More2", VaadinIcon.COG.create(), View4.class)
                            .build())
                    .add("Menu", VaadinIcon.MENU.create(), View5.class)
                    .addClickable("Set Behaviour FOOTER", VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant), FOOTER);
        } else {
            return AppLayoutBuilder.get(variant)
                    .withTitle("App Layout")
                    .withAppBar(AppBarBuilder() (new AppBarNotificationButton(VaadinIcon.BELL.create(), notifications))
                    .addToAppBar(new AppBarNotificationButton(VaadinIcon.BELL.create(), notifications))
                    .withDesign(AppLayoutDesign.MATERIAL)
                    .addClickable("Set Behaviour 1", VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant), HEADER)
                    .add("Home", VaadinIcon.HOME.create(), badge, View1.class)
                    .add("Contact", VaadinIcon.SPLINE_CHART.create(), View2.class)
                    .addClickable("Set Behaviour 2", VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant), FOOTER)
                    .add("More", VaadinIcon.CONNECT.create(), View3.class, FOOTER);
        }
    }

    private void reloadNotifications() {
       /* if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badge.clearCount();
        notifications.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(5000);
                    addNotification(MEDIUM);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();*/
    }

    private void addNotification(Priority priority) {
        getUI().ifPresent(ui -> ui.accessSynchronously(() -> {
            badge.increase();
            notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description" + badge.getCount(), priority));
        }));
    }

    private void setDrawerVariant(Behaviour variant) {
        this.variant = variant;
        reloadConfiguration();
    }

    private void openModeSelector(Behaviour variant) {
        new BehaviourSelector(variant, variant1 -> setDrawerVariant(variant1)).open();
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
            group.setValue(current);
            layout.add(group);
            group.addValueChangeListener(singleSelectionEvent -> {
                consumer.accept(singleSelectionEvent.getValue());
                close();
            });
        }
    }
}
