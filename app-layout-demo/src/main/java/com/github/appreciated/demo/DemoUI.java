package com.github.appreciated.demo;

import com.github.appreciated.layout.drawer.Left;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Theme("demo")
@Title("App Layout Add-on Demo")
@Push
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        try {
            setContent(new Left());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attach() {

    }
}
