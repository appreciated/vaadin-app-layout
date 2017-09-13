package com.github.appreciated.demo;

import com.github.appreciated.builder.DrawerVariant;
import com.github.appreciated.builder.NavigationDrawerBuilder;
import com.github.appreciated.demo.views.View1;
import com.github.appreciated.demo.views.View2;
import com.github.appreciated.demo.views.View3;
import com.github.appreciated.layout.drawer.AbstractNavigationDrawer;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("App Layout Add-on Demo")
@Push
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout left = new VerticalLayout();
        left.setMargin(false);
        VerticalLayout right = new VerticalLayout();
        right.setWidthUndefined();

        setDrawerVariant(left, DrawerVariant.LEFT);

        ComboBox<DrawerVariant> variants = new ComboBox<>();
        variants.setItems(DrawerVariant.LEFT, DrawerVariant.LEFT_OVERLAY, DrawerVariant.LEFT_RESPONSIVE, DrawerVariant.LEFT_RESPONSIVE_OVERLAY, DrawerVariant.LEFT_RESPONSIVE_OVERLAY_NOAPPBAR);
        variants.addValueChangeListener(valueChangeEvent -> {
            setDrawerVariant(left, valueChangeEvent.getValue());
        });
        variants.setValue(DrawerVariant.LEFT);
        right.addComponent(new Panel("Variants", new VerticalLayout(variants)));

        HorizontalLayout layout = new HorizontalLayout(left, right);
        layout.setSpacing(false);
        layout.setExpandRatio(left, 1);
        layout.setSizeFull();
        setContent(layout);
    }

    private void setDrawerVariant(VerticalLayout rightside, DrawerVariant variant) {
        rightside.removeAllComponents();

        AbstractNavigationDrawer drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("App Layout Demo")
                .withAppBarElement(getBorderlessButtonWithIcon(VaadinIcons.ELLIPSIS_DOTS_V))
                .withDefaultNavigationView(View1.class)
                .withNavigationElement("Home", VaadinIcons.HOME, View2.class)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View3.class)
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View1.class)
                .withSection("More")
                .withNavigationElement("More", VaadinIcons.PLUS, View2.class)
                .withNavigationElement("Menu", VaadinIcons.MENU, View3.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View1.class)
                .withSection("Settings")
                .withNavigationElement("Preferences", VaadinIcons.COG, View2.class)
                .build();
        rightside.addComponent(drawer);
    }

    private Button getBorderlessButtonWithIcon(VaadinIcons icon) {
        Button button = new Button(icon);
        button.setWidth("64px");
        button.setHeight("64px");
        button.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.BUTTON_ICON_ONLY);
        return button;
    }
}