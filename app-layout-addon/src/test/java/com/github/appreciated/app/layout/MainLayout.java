package com.github.appreciated.app.layout;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftSectionItem;
import com.github.appreciated.app.layout.component.menu.top.item.TopClickableItem;
import com.github.appreciated.app.layout.component.menu.top.item.TopNavigationItem;
import com.github.appreciated.app.layout.component.menu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.notification.entitiy.Priority;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

import java.util.function.Consumer;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;
import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends AppLayoutRouterLayout {
    DefaultNotificationHolder notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
    DefaultBadgeHolder badgeHolder = new DefaultBadgeHolder();
    private Behaviour variant = Behaviour.LEFT_RESPONSIVE;
    private Thread currentThread;

    public MainLayout() {
        init(getLayoutConfiguration(variant));
        reloadNotifications();
    }

    private void reloadNotifications() {
        if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badgeHolder.clearCount();
        notificationHolder.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    //Thread.sleep(5000);
                    getUI().ifPresent(ui -> ui.access(() -> {
                        addNotification(MEDIUM);
                        badgeHolder.increase();
                        badgeHolder.increase();
                    }));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();
    }

    private AppLayout getLayoutConfiguration(Behaviour variant) {
        this.variant = variant;
        if (!this.variant.isTop()) {
            LeftNavigationItem home = new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class);
            LeftNavigationItem menu = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View9.class);

            notificationHolder.bind(home.getBadge());
            badgeHolder.bind(menu.getBadge());
            return AppLayoutBuilder
                    .get(this.variant)
                    .withTitle("App Layout")
                    .withIcon("/frontend/images/logo.png")
                    .withAppBar(AppBarBuilder
                            .get()
                            .add(new AppBarNotificationButton(VaadinIcon.BELL, notificationHolder))
                            .build())
                    .withAppMenu(LeftAppMenuBuilder
                            .get()
                            .addToSection(new LeftHeaderItem("App-Layout",
                                    "Version 2.0.8",
                                    "/frontend/images/logo.png"
                            ), HEADER)
                            .addToSection(new LeftClickableItem("Set Behaviour HEADER",
                                    VaadinIcon.COG.create(),
                                    clickEvent -> openModeSelector(this.variant)
                            ), HEADER)
                            .add(home)
                            .add(new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class))
                            .add(LeftSubMenuBuilder
                                    .get("My Submenu", VaadinIcon.PLUS.create())
                                    .add(LeftSubMenuBuilder
                                            .get("My Submenu", VaadinIcon.PLUS.create())
                                            .add(new LeftNavigationItem("Charts",
                                                    VaadinIcon.SPLINE_CHART.create(),
                                                    View2.class
                                            ))
                                            .add(new LeftNavigationItem("Contact",
                                                    VaadinIcon.CONNECT.create(),
                                                    View3.class
                                            ))
                                            .add(new LeftNavigationItem("More",
                                                    VaadinIcon.COG.create(),
                                                    View4.class
                                            ))
                                            .build())
                                    .add(new LeftNavigationItem("Contact1",
                                            VaadinIcon.CONNECT.create(),
                                            View5.class
                                    ))
                                    .add(new LeftNavigationItem("More1", VaadinIcon.COG.create(), View6.class))
                                    .build())
                            .add(new LeftSectionItem())
                            .add(LeftSubMenuBuilder
                                    .get("My Submenu", VaadinIcon.PLUS.create())
                                    .add(new LeftNavigationItem("Contact2",
                                            VaadinIcon.CONNECT.create(),
                                            View7.class
                                    ))
                                    .add(new LeftNavigationItem("More2", VaadinIcon.COG.create(), View8.class))
                                    .build())
                            .add(new LeftSectionItem("Test"))
                            .add(menu)
                            .addToSection(new LeftClickableItem("Set Behaviour FOOTER",
                                    VaadinIcon.COG.create(),
                                    clickEvent -> openModeSelector(this.variant)
                            ), FOOTER)
                            .build())
                    .build();
        } else {
            return AppLayoutBuilder
                    .get(this.variant)
                    .withTitle("App Layout")
                    .withAppBar(AppBarBuilder
                            .get()
                            .add(new AppBarNotificationButton(VaadinIcon.BELL, notificationHolder))
                            .build())
                    .withAppMenu(TopAppMenuBuilder
                            .get()
                            .addToSection(new TopClickableItem("Set Behaviour 1",
                                    VaadinIcon.COG.create(),
                                    clickEvent -> openModeSelector(this.variant)
                            ), HEADER)
                            .add(new TopNavigationItem("Home", VaadinIcon.HOME.create(), View1.class))
                            .add(new TopNavigationItem("Contact", VaadinIcon.SPLINE_CHART.create(), View2.class))
                            .addToSection(new TopClickableItem("Set Behaviour 2",
                                    VaadinIcon.COG.create(),
                                    clickEvent -> openModeSelector(this.variant)
                            ), FOOTER)
                            .addToSection(
                                    new TopNavigationItem("More", VaadinIcon.CONNECT.create(), View3.class),
                                    FOOTER
                            )
                            .build())
                    .build();
        }
    }

    private void addNotification(Priority priority) {
        notificationHolder.addNotification(new DefaultNotification(
                "Title" + badgeHolder.getCount(),
                "Description ..............................................."
                        + badgeHolder.getCount(),
                priority
        ));
    }

    private void openModeSelector(Behaviour variant) {
        new BehaviourSelector(variant, this::setDrawerVariant).open();
    }

    private void setDrawerVariant(Behaviour variant) {
        this.variant = variant;
        init(getLayoutConfiguration(variant));
    }

    class BehaviourSelector extends Dialog {
        public BehaviourSelector(Behaviour current, Consumer<Behaviour> consumer) {
            VerticalLayout layout = new VerticalLayout();
            add(layout);
            RadioButtonGroup<Behaviour> group = new RadioButtonGroup<>();
            group.getElement()
                    .getStyle()
                    .set("display", "flex");
            group.getElement()
                    .getStyle()
                    .set("flexDirection", "column");
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
                    Behaviour.TOP_LARGE
            );
            group.setValue(current);
            layout.add(group);
            group.addValueChangeListener(singleSelectionEvent -> {
                consumer.accept(singleSelectionEvent.getValue());
                close();
            });
        }
    }
}
