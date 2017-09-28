package com.github.appreciated.demo;


import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.NavigationDrawerBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.NotificationAppBarButton;
import com.github.appreciated.app.layout.drawer.AppLayout;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.HEADER;

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
        setDrawerVariant(DrawerVariant.LEFT);
        setContent(holder);
        holder.setSizeFull();
        notifications.setNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));
    }

    @Override
    public void attach() {
        super.attach();
        addNotification();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                addNotification();
            }
        }).start();
    }

    private void addNotification() {
        DemoUI.this.access(() -> {
            badge.increase();
            notifications.addNotification(new DefaultNotification("Title" + badge.getCount(), "Description" + badge.getCount()));
        });
    }

    private void setDrawerVariant(DrawerVariant variant) {
        holder.removeAllComponents();


        AppLayout drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("Demo")
                .withAppBarElement(getVariantCombo(variant))
                .withAppBarElement(new NotificationAppBarButton(notifications))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.DEFAULT)
                .withNavigationElement(new MenuHeader("App Layout", "Version 0.9.2", new ThemeResource("logo.png")), HEADER)
                .withNavigationElement("Home", VaadinIcons.HOME, badge, View1.class)
                .withSubmenuElement(
                        SubmenuBuilder.get("My Submenu", VaadinIcons.PLUS)
                                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                                .withNavigationElement("More", VaadinIcons.COG, View4.class)
                                .build())
                .withNavigationElement("Menu", VaadinIcons.MENU, View5.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View6.class)
                .withClickableElement("Click Me", VaadinIcons.QUESTION, clickEvent -> {/*Click Event*/})
                .withNavigationElement("Preferences", VaadinIcons.COG, View7.class, FOOTER)
                .build();
        holder.addComponent(drawer);
    }

    ComboBox getVariantCombo(DrawerVariant variant) {
        ComboBox<DrawerVariant> variants = new ComboBox<>();
        variants.addStyleNames(ValoTheme.COMBOBOX_BORDERLESS, ValoTheme.CHECKBOX_SMALL, ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        variants.setWidth("300px");
        variants.setItems(DrawerVariant.LEFT,
                DrawerVariant.LEFT_OVERLAY,
                DrawerVariant.LEFT_RESPONSIVE,
                DrawerVariant.LEFT_RESPONSIVE_OVERLAY,
                DrawerVariant.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
                DrawerVariant.LEFT_RESPONSIVE_SMALL,
                DrawerVariant.LEFT_RESPONSIVE_SMALL_NO_APP_BAR);
        variants.setValue(variant);
        variants.addValueChangeListener(valueChangeEvent -> setDrawerVariant(valueChangeEvent.getValue()));
        return variants;
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public static class View1 extends HorizontalLayout implements View {
        public View1() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View2 extends HorizontalLayout implements View {
        public View2() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View3 extends HorizontalLayout implements View {
        public View3() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View4 extends HorizontalLayout implements View {
        public View4() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View5 extends HorizontalLayout implements View {
        public View5() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View6 extends HorizontalLayout implements View {
        public View6() {
            addComponent(new Label(getClass().getName()));
        }
    }

    public static class View7 extends HorizontalLayout implements View {
        public View7() {
            addComponent(new Label(getClass().getName()));
        }
    }

}