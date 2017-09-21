package com.github.appreciated.demo;


import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.NavigationDrawerBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.entities.BadgeStatus;
import com.github.appreciated.app.layout.component.AppBarButton;
import com.github.appreciated.app.layout.component.RoundResourceButton;
import com.github.appreciated.app.layout.drawer.AbstractNavigationDrawer;
import com.github.appreciated.demo.views.View1;
import com.github.appreciated.demo.views.View2;
import com.github.appreciated.demo.views.View3;
import com.vaadin.annotations.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.appreciated.app.layout.Styles.APP_LAYOUT_MENU_BAR_ELEMENT;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.NavigationDrawerBuilder.Position.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("demo")
@Title("App Layout Add-on Demo")
@Push(PushMode.AUTOMATIC)
public class DemoUI extends UI {

    private VerticalLayout holder;
    private BadgeStatus badgeStatus;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        badgeStatus = new BadgeStatus("0");

        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(DrawerVariant.LEFT);
        setContent(holder);
        holder.setSizeFull();

        final int[] i = {0};

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("i = " + i[0]);
                DemoUI.this.access(() -> {
                    badgeStatus.setStatus("" + i[0]++);
                });
                DemoUI.this.push();
            }
        }, 0, 5000);
    }

    private void setDrawerVariant(DrawerVariant variant) {
        holder.removeAllComponents();

        AbstractNavigationDrawer drawer = NavigationDrawerBuilder.get()
                .withVariant(variant)
                .withTitle("My Appbar Title")
                .withAppBarIconComponent(new RoundResourceButton(new ThemeResource("logo.png"), "50px", "50px"))
                .withAppBarElement(new AppBarButton(VaadinIcons.ELLIPSIS_V, clickEvent -> {/*Click Event*/}))
                .withDefaultNavigationView(View1.class)
                .withDesign(AppBarDesign.DEFAULT)
                .withNavigationElement(getMenuHeader(), HEADER)
                .withNavigationElement("Home", VaadinIcons.HOME, badgeStatus, View1.class)
                .withNavigationElement("Charts", VaadinIcons.SPLINE_CHART, View2.class)
                .withNavigationElement("Contact", VaadinIcons.CONNECT, View3.class)
                .withNavigationElement("More", VaadinIcons.PLUS, View2.class)
                .withNavigationElement("Menu", VaadinIcons.MENU, View3.class)
                .withNavigationElement("Elements", VaadinIcons.LIST, View2.class)
                .withNavigationElement("Preferences", VaadinIcons.COG, View3.class, FOOTER)
                .build();
        holder.addComponent(drawer);
        Label content = new Label("My Content");
        content.addStyleName(ValoTheme.LABEL_H2);
        drawer.getContentHolder().addComponent(content);
        drawer.getContentHolder().setComponentAlignment(content, Alignment.TOP_CENTER);
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
        Label description = new Label("Version 0.8.2");
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

}