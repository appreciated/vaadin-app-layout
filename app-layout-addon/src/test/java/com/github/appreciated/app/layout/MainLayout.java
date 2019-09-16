package com.github.appreciated.app.layout;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.addons.notification.entity.Priority;
import com.github.appreciated.app.layout.addons.profile.builder.AppBarImageProfileButtonBuilder;
import com.github.appreciated.app.layout.addons.profile.builder.AppBarProfileButtonBuilder;
import com.github.appreciated.app.layout.addons.search.SearchButton;
import com.github.appreciated.app.layout.addons.search.overlay.SearchOverlayButton;
import com.github.appreciated.app.layout.addons.search.overlay.SearchOverlayButtonBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.AppLayout;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.applayout.TopLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftSectionItem;
import com.github.appreciated.app.layout.component.menu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.top.item.TopClickableItem;
import com.github.appreciated.app.layout.component.menu.top.item.TopNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.navigation.TestSearchResult;
import com.github.appreciated.card.RippleClickableCard;
import com.github.appreciated.card.content.Item;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.TextRenderer;

import java.util.Arrays;
import java.util.function.Consumer;

import static com.github.appreciated.app.layout.addons.notification.entity.Priority.MEDIUM;
import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;

/**
 * The main view contains a button and a template element.
 */

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
    private DefaultBadgeHolder badgeHolder = new DefaultBadgeHolder();
    private Class<? extends AppLayout> variant = LeftLayouts.LeftResponsiveHybrid.class;
    private Thread currentThread;

    private SearchOverlayButton<TestSearchResult> button;
    private ListDataProvider<TestSearchResult> dataProvider;

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

    private AppLayout getLayoutConfiguration(Class<? extends AppLayout> variant) {
        this.variant = variant;

        dataProvider = new ListDataProvider<>(Arrays.asList(
                new TestSearchResult("Header1", "Description1"),
                new TestSearchResult("Header2", "Description2"),
                new TestSearchResult("Header3", "Description3"),
                new TestSearchResult("Header4", "Description4"),
                new TestSearchResult("Header5", "Description5"),
                new TestSearchResult("Header6", "Description6"),
                new TestSearchResult("Header7", "Description7"),
                new TestSearchResult("Header8", "Description8"),
                new TestSearchResult("Header9", "Description9"),
                new TestSearchResult("Header10", "Description10")
        ));

        button = new SearchOverlayButtonBuilder<TestSearchResult>()
                .withDataProvider(dataProvider)
                .withQueryProvider(s -> new Query<>(testEntity -> !s.equals("") && testEntity.getHeader().startsWith(s)))
                .withDataViewProvider(queryResult -> {
                    RippleClickableCard card = new RippleClickableCard(new Item(queryResult.getHeader(), queryResult.getDescription()));
                    card.setWidthFull();
                    card.setBackground("var(--lumo-base-color)");
                    return card;
                })
                .withQueryResultListener(testSearchResult -> Notification.show(testSearchResult.getHeader()))
                .build();
        if (this.variant != TopLayouts.Top.class && this.variant != TopLayouts.TopLarge.class) {
            LeftNavigationItem home = new LeftNavigationItem("Home", VaadinIcon.HOME.create(), View1.class);
            LeftNavigationItem menu = new LeftNavigationItem("Menu", VaadinIcon.MENU.create(), View9.class);

            notificationHolder.bind(home.getBadge());
            badgeHolder.bind(menu.getBadge());
            AppLayout build = AppLayoutBuilder
                    .get(this.variant)
                    .withTitle("App Layout")
                    .withIcon("/frontend/images/logo.png")
                    .withAppBar(AppBarBuilder
                            .get()
                            .add(button,
                                    AppBarProfileButtonBuilder.get()
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .build(),
                                    AppBarImageProfileButtonBuilder.get("/frontend/images/logo.png")
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .build(),
                                    new SearchButton().withValueChangeListener(event -> Notification.show(event.getValue())),
                                    new NotificationButton<>(VaadinIcon.BELL, notificationHolder))
                            .build()
                    )
                    .withAppMenu(LeftAppMenuBuilder
                            .get()
                            .addToSection(HEADER,
                                    new LeftHeaderItem("App-Layout", "Version 4.0.0", "/frontend/images/logo.png"),
                                    new LeftClickableItem("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> openModeSelector(this.variant))
                            )
                            .add(home,
                                    new LeftNavigationItem("Grid", VaadinIcon.TABLE.create(), GridTest.class),
                                    LeftSubMenuBuilder.get("My Submenu 1", VaadinIcon.PLUS.create())
                                            .add(LeftSubMenuBuilder.get("My Submenu 2", VaadinIcon.PLUS.create())
                                                            .add(new LeftNavigationItem("Charts", VaadinIcon.SPLINE_CHART.create(), View2.class),
                                                                    new LeftNavigationItem("Contact", VaadinIcon.CONNECT.create(), View3.class),
                                                                    new LeftNavigationItem("More", VaadinIcon.COG.create(), View4.class))
                                                            .build(),
                                                    new LeftNavigationItem("Contact1", VaadinIcon.CONNECT.create(), View5.class),
                                                    new LeftNavigationItem("More1", VaadinIcon.COG.create(), View6.class))
                                            .build(),
                                    new LeftSectionItem(),
                                    LeftSubMenuBuilder.get("My Submenu 3")
                                            .add(new LeftNavigationItem("Contact2", VaadinIcon.CONNECT.create(), View7.class),
                                                    new LeftNavigationItem("More2", VaadinIcon.COG.create(), View8.class))
                                            .build(),
                                    new LeftSectionItem("Test"),
                                    menu
                            )
                            .addToSection(FOOTER,
                                    new LeftClickableItem("Set Behaviour FOOTER", VaadinIcon.COG.create(), clickEvent -> openModeSelector(this.variant))
                            )
                            .build())
                    .withUpNavigation()
                    .build();
            return build;
        } else {
            return AppLayoutBuilder.get(this.variant)
                    .withTitle("App Layout")
                    .withAppBar(AppBarBuilder.get()
                            .add(button,
                                    AppBarProfileButtonBuilder.get()
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .withItem("Profile", event -> Notification.show("Profile clicked"))
                                            .build(),
                                    new NotificationButton<>(VaadinIcon.BELL, notificationHolder))
                            .build())
                    .withAppMenu(TopAppMenuBuilder.get()
                            .add(new TopClickableItem("Set Behaviour 1", VaadinIcon.COG.create(), event -> openModeSelector(this.variant)),
                                    new TopNavigationItem("Home", VaadinIcon.HOME.create(), View1.class),
                                    new TopNavigationItem("Contact", VaadinIcon.SPLINE_CHART.create(), View2.class),
                                    new TopClickableItem("Set Behaviour 2", VaadinIcon.COG.create(), event -> openModeSelector(this.variant)),
                                    new TopNavigationItem("More", VaadinIcon.CONNECT.create(), View3.class))
                            .build())
                    .withUpNavigation()
                    .build();
        }
    }

    private void addNotification(Priority priority) {
        notificationHolder.add(new DefaultNotification(
                "Title" + badgeHolder.getCount(),
                "Description ..............................................."
                        + badgeHolder.getCount(),
                priority
        ));
    }

    private void openModeSelector(Class<? extends AppLayout> variant) {
        new LayoutSelector(variant, this::setDrawerVariant).open();
    }

    private void setDrawerVariant(Class<? extends AppLayout> variant) {
        this.variant = variant;
        init(getLayoutConfiguration(variant));
    }

    class LayoutSelector extends Dialog {
        public LayoutSelector(Class<? extends AppLayout> current, Consumer<Class<? extends AppLayout>> consumer) {
            VerticalLayout layout = new VerticalLayout();
            add(layout);
            RadioButtonGroup<Class<? extends AppLayout>> group = new RadioButtonGroup<>();
            group.getElement()
                    .getStyle()
                    .set("display", "flex");
            group.getElement()
                    .getStyle()
                    .set("flexDirection", "column");
            group.setItems(LeftLayouts.Left.class,
                    LeftLayouts.LeftOverlay.class,
                    LeftLayouts.LeftResponsive.class,
                    LeftLayouts.LeftResponsiveDouble.class,
                    LeftLayouts.LeftHybrid.class,
                    LeftLayouts.LeftHybridSmall.class,
                    LeftLayouts.LeftResponsiveHybrid.class,
                    LeftLayouts.LeftResponsiveHybridNoAppBar.class,
                    LeftLayouts.LeftResponsiveHybridOverlayNoAppBar.class,
                    LeftLayouts.LeftResponsiveOverlay.class,
                    LeftLayouts.LeftResponsiveOverlayNoAppBar.class,
                    LeftLayouts.LeftResponsiveSmall.class,
                    LeftLayouts.LeftResponsiveSmallNoAppBar.class,
                    TopLayouts.Top.class,
                    TopLayouts.TopLarge.class
            );
            group.setRenderer(new TextRenderer<>(Class::getSimpleName));
            group.setValue(current);
            layout.add(group);
            group.addValueChangeListener(singleSelectionEvent -> {
                consumer.accept(singleSelectionEvent.getValue());
                close();
            });
        }
    }
}
