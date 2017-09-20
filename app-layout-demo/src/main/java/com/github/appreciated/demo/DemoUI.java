package com.github.appreciated.demo;


import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.NavigationDrawerBuilder;
import com.github.appreciated.app.layout.drawer.AbstractNavigationDrawer;
import com.github.appreciated.demo.views.View1;
import com.github.appreciated.demo.views.View2;
import com.github.appreciated.demo.views.View3;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Add-on Demo")
@Push
public class DemoUI extends UI {

    private VerticalLayout holder;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(DrawerVariant.LEFT);
        setContent(holder);
        holder.setSizeFull();
    }

    private void setDrawerVariant(DrawerVariant variant) {
        holder.removeAllComponents();

        AbstractNavigationDrawer drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("App Layout Demo")
                .withAppBarElement(getVariantCombo(variant))
                .withDefaultNavigationView(View1.class)
                .withNavigationElement("Home", VaadinIcons.HOME, label -> "1", View1.class, HEADER)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                .withSection("More")
                .withNavigationElement("More", VaadinIcons.PLUS, View2.class)
                .withNavigationElement("Menu", VaadinIcons.MENU, View3.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View2.class)
                .withSection("Settings")
                .withNavigationElement("Preferences", VaadinIcons.COG, View3.class)
                .withClickableElement("Custom Action", VaadinIcons.EDIT, clickEvent -> Notification.show("Yay!"), FOOTER)
                .build();
        holder.addComponent(drawer);
        drawer.getContentHolder().addComponent(new DateTimeField());
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
}