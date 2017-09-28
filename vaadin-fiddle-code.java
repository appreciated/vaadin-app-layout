package org.vaadin.vaadinfiddle;

import com.github.appreciated.app.layout.builder.DrawerVariant;
import com.github.appreciated.app.layout.builder.NavigationDrawerBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.NavigationBadgeButton;
import com.github.appreciated.app.layout.component.NavigationButton;
import com.github.appreciated.app.layout.component.RoundResourceButton;
import com.github.appreciated.app.layout.drawer.AppLayout;
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
@Theme("mytheme")
@Title("App Layout Add-on Demo")
@Push
public class MyUI extends UI {

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
                .withNavigationElement(new MenuHeader(""), HEADER)
                .withNavigationElement(new NavigationBadgeButton("Home", VaadinIcons.HOME, nholder))
                .withNavigationElement(new NavigationButton("Charts", VaadinIcons.SPLINE_CHART))
                .withNavigationElement(new NavigationButton("Contact", VaadinIcons.CONNECT))
                .withNavigationElement(new NavigationButton("More", VaadinIcons.PLUS))
                .withNavigationElement(new NavigationButton("Menu", VaadinIcons.MENU))
                .withNavigationElement(new NavigationButton("Elements", VaadinIcons.LIST))
                .withClickableElement("Click Me", VaadinIcons.QUESTION, clickEvent -> {/*Click Event*/})
                .withNavigationElement(new NavigationButton("Preferences", VaadinIcons.COG), FOOTER)
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
    @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
    public static class Servlet extends VaadinServlet {
    }
}