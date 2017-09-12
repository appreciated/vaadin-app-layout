package com.github.appreciated.demo;

import com.github.appreciated.builder.DrawerVariant;
import com.github.appreciated.builder.NavigationDrawerBuilder;
import com.github.appreciated.demo.views.View1;
import com.github.appreciated.demo.views.View2;
import com.github.appreciated.demo.views.View3;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
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
        setContent(NavigationDrawerBuilder.get()
                .withVariant(DrawerVariant.LEFT_OVERLAY)
                .withTitle("App Layout Demo")
                .withAppBarElement(getBorderlessButtonWithIcon(VaadinIcons.ELLIPSIS_DOTS_V))
                .withDefaultNavigationView(View2.class)
                .withNavigationElement("Home", VaadinIcons.HOME, View1.class)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, new View2())
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                .withSection("More")
                .withNavigationElement("More", VaadinIcons.PLUS, View1.class)
                .withNavigationElement("Menu", VaadinIcons.MENU, View2.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View3.class)
                .withSection("Settings")
                .withNavigationElement("Preferences", VaadinIcons.COG, View1.class)
                .build());
    }

    private Button getBorderlessButtonWithIcon(VaadinIcons icon) {
        Button button = new Button(icon);
        button.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        return button;
    }
}