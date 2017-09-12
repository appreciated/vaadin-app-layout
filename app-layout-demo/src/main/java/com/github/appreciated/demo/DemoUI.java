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
                .withVariant(DrawerVariant.LEFT_RESPONSIVE_OVERLAY)
                .withTitle("my Title")
                .withAppBarElement(getBorderlessButtonWithIcon(VaadinIcons.ELLIPSIS_DOTS_V))
                .withNavigationElement(new Button("Test1234"))
                .withNavigationView("MyView1", View1.class)
                .withNavigationView("MyView2", new View2())
                .withNavigationView("MyView3", View3.class)
                .build());
    }

    private Button getBorderlessButtonWithIcon(VaadinIcons icon) {
        Button button = new Button(icon);
        button.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        return button;
    }
}
