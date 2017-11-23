package com.github.appreciated.demo;


import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.NotificationAppBarButton;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;
import java.util.function.Consumer;

import static com.github.appreciated.app.layout.builder.AppLayoutBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.AppLayoutBuilder.Position.HEADER;
import static com.github.appreciated.app.layout.builder.entities.DefaultNotification.Priority.*;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Demo")
@Push
public class DemoUI extends UI {

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private VerticalLayout holder;

    @Override
    protected void init(VaadinRequest request) {
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(Behaviour.LEFT_RESPONSIVE);
        setContent(holder);
        holder.setSizeFull();
        notifications.setNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));
    }

    @Override
    public void attach() {
        super.attach();
        addNotification(LOW);
        addNotification(LOW);
        addNotification(MEDIUM);
        addNotification(MEDIUM);
        addNotification(HIGH);
        addNotification(HIGH);
    }

    private void addNotification(DefaultNotification.Priority priority) {
        DemoUI.this.access(() -> {
            badge.increase();
            notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description" + badge.getCount(), priority));
        });
        badge.increase();
    }

    private void setDrawerVariant(Behaviour variant) {
        holder.removeAllComponents();
        AppLayout drawer = AppLayoutBuilder.get(variant)
                .withTitle("App Layout")
                .addToAppBar(new NotificationAppBarButton(notifications, true))
                .withViewNameInterceptor(new DefaultViewNameInterceptor())
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.MATERIAL)
                .withNavigatorConsumer(navigator -> {/* Do someting with it */})
                .add(new MenuHeader("Version 0.9.17", new ThemeResource("logo.png")), HEADER)
                .add("Home", VaadinIcons.HOME, badge, new View1())
                .add(SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                                .add("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                                .add("Contact", VaadinIcons.CONNECT, View3.class)
                                .add("More", VaadinIcons.COG, View4.class)
                                .build())
                .add("Menu", VaadinIcons.MENU, View5.class)
                .add("Elements", VaadinIcons.LIST, View6.class)
                .addClickable("Set Behaviour", VaadinIcons.COG, clickEvent -> openModeSelector(variant), FOOTER)
                .build();
        holder.addComponent(drawer);
        getNavigator().navigateTo("");
    }

    private void openModeSelector(Behaviour variant) {
        UI.getCurrent().addWindow(new BehaviourSelector(variant, variant1 -> setDrawerVariant(variant1)));
    }

    public static class View1 extends AbstractView {
        @Override
        String getViewName() {
            return getClass().getName();
        }
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