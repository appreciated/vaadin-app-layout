package com.github.appreciated.demo;

import com.github.appreciated.layout.drawer.Responsive;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Title("App Layout Add-on Demo")
@Push
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        try {
            Responsive layout = new Responsive();
            for (int i = 0; i < 100; i++) {
                layout.addComponent(new Label("Test1234123412341234"));
            }
            setContent(layout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attach() {

    }
}
