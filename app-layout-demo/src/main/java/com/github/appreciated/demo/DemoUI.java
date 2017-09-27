package com.github.appreciated.demo;


import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.NavigationDrawerBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.RoundResourceButton;
import com.github.appreciated.app.layout.drawer.AppLayout;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BAR_ELEMENT;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Add-on Demo")
@JavaScript("vaadin://bower_components/webcomponentsjs/webcomponents-lite.js")
@Push
public class DemoUI extends UI {

    final int[] i = {0};
    DefaultNotificationHolder nholder = new DefaultNotificationHolder();
    private VerticalLayout holder;

    @Override
    protected void init(VaadinRequest request) {
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(DrawerVariant.LEFT);
        setContent(holder);
        holder.setSizeFull();
        nholder.setNotificationClickedListener(newStatus -> Notification.show(newStatus.getTitle()));
    }

    @Override
    public void attach() {
        super.attach();
        addNewNotification();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getUI().access(() -> {
                getUI().getNavigator().navigateTo("Charts");
                getUI().push();
            });
        }).start();
    }

    private void addNewNotification() {
        new Thread(() -> {
            getUI().access(() -> {
                nholder.addNotification(new DefaultNotification("Title" + i[0], "Description" + i[0]++));
            });
        }).start();
    }

    private void setDrawerVariant(DrawerVariant variant) {
        holder.removeAllComponents();

        AppLayout drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("My Appbar Title")
                .withAppBarIconComponent(new RoundResourceButton(new ThemeResource("logo.png"), "50px", "50px"))
                .withAppBarElement(getVariantCombo(variant))
                //.withAppBarElement(new NotificationAppBarButton(nholder))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                //.withAppBarElement(new AppBarButton(VaadinIcons.SEARCH))
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.DEFAULT)
                .withNavigationElement(getMenuHeader(), HEADER)
                .withNavigationElement("Home", VaadinIcons.HOME, nholder, View1.class)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                .withNavigationElement("More", VaadinIcons.PLUS, View4.class)
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

    Component getMenuHeader() {
        Label name = new Label("Vaadin App Layout");
        name.addStyleName(ValoTheme.LABEL_H4);
        Label description = new Label("Version 0.8.9");
        description.addStyleName(ValoTheme.LABEL_SMALL);
        VerticalLayout layout = new VerticalLayout(getResourceButton(), name, description);
        layout.addStyleName(APP_LAYOUT_MENU_BAR_ELEMENT);
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setMargin(new MarginInfo(true, false));
        return layout;
    }

    RoundResourceButton getResourceButton() {
        return getResourceButton(null, null);
    }

    RoundResourceButton getResourceButton(String width, String height) {
        if (width == null || height == null) {
            width = "75px";
            height = "75px";
        }
        return new RoundResourceButton(new ThemeResource("logo.png"), width, height);
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