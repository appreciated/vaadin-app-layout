package com.github.appreciated.demo;


import com.github.appreciated.builder.DrawerVariant;
import com.github.appreciated.builder.NavigationDrawerBuilder;
import com.github.appreciated.builder.component.NavigationButton;
import com.github.appreciated.demo.views.View1;
import com.github.appreciated.demo.views.View2;
import com.github.appreciated.demo.views.View3;
import com.github.appreciated.layout.drawer.AbstractNavigationDrawer;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("App Layout Add-on Demo")
@Push
public class DemoUI extends UI {

    private VerticalLayout left;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        left = new VerticalLayout();
        left.setMargin(false);
        setDrawerVariant(left, DrawerVariant.LEFT);
        setContent(left);
    }

    private void setDrawerVariant(VerticalLayout rightside, DrawerVariant variant) {
        rightside.removeAllComponents();

        AbstractNavigationDrawer drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("App Layout Demo")
                .withAppBarElement(getVariantCombo(variant))
                .withDefaultNavigationView(View1.class)
                .withNavigationElement("Home", VaadinIcons.HOME, View1.class)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                .withSection("More")
                .withNavigationElement("More", VaadinIcons.PLUS, View1.class)
                .withNavigationElement("Menu", VaadinIcons.MENU, View2.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View3.class)
                .withSection("Settings")
                .withNavigationElement("Preferences", VaadinIcons.COG, View1.class)
                .withClickableElement("Custom Action", VaadinIcons.EDIT, clickEvent -> Notification.show("Yay!"))
                .build();
        rightside.addComponent(drawer);
        drawer.addComponent(new DateTimeField());
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
        variants.addValueChangeListener(valueChangeEvent -> {
            setDrawerVariant(left, valueChangeEvent.getValue());
        });
        return variants;
    }
}