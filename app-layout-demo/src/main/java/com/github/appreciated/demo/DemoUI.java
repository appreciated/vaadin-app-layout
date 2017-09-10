package com.github.appreciated.demo;

import com.github.appreciated.layout.drawer.ResponsiveNavigationDrawer;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Theme("demo")
@Title("App Layout Add-on Demo")
@Push
public class DemoUI extends UI {

    private ResponsiveNavigationDrawer layout;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        try {
            layout = new ResponsiveNavigationDrawer();
            for (int i = 0; i < 100; i++) {
                layout.addComponent(new Label("Test"+i));
            }
            for (int i = 0; i < 10; i++) {
                layout.addNavigationElement(new Button("Test"+i));
            }
            for (int i = 0; i < 3; i++) {
                layout.addAppBarElement(new Button(VaadinIcons.ABACUS));
            }
            setContent(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
