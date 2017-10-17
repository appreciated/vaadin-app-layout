package org.vaadin.vaadinfiddle;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.design.AppBarDesign;
import com.github.appreciated.app.layout.builder.elements.SubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotification;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.component.*;
import com.github.appreciated.app.layout.interceptor.DefaultViewNameInterceptor;
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
import static com.github.appreciated.app.layout.builder.AppLayoutBuilder.Position.FOOTER;
import static com.github.appreciated.app.layout.builder.AppLayoutBuilder.Position.HEADER;

@Viewport("initial-scale=1, maximum-scale=1")
@Theme("mytheme")
@Title("App Layout Add-on Demo")
@Push
public class MyUI extends UI {

    DefaultBadgeHolder badge = new DefaultBadgeHolder();
    private VerticalLayout holder;

    @Override
    protected void init(VaadinRequest request) {
        holder = new VerticalLayout();
        holder.setMargin(false);
        setDrawerVariant(Behaviour.LEFT);
        setContent(holder);
        holder.setSizeFull();
    }

    @Override
    public void attach() {
        super.attach();
        addNewNotification();
    }

    private void addNewNotification() {
        badge.increase();
    }

    private void setDrawerVariant(Behaviour variant) {
        holder.removeAllComponents();

        AppLayout drawer = AppLayoutBuilder.get()
                .withBehaviour(variant)
                .withTitle("My Appbar Title")
                .addToAppBar(getVariantCombo(variant))
                .add(new MenuHeader("App Layout", "Version 0.9.5", new ThemeResource("logo.png")), HEADER)
                .add(new NavigationBadgeButton("Home", VaadinIcons.HOME, badge))
                .add(new NavigationButton("Charts", VaadinIcons.SPLINE_CHART))
                .add(new NavigationButton("Contact", VaadinIcons.CONNECT))
                .add(new NavigationButton("More", VaadinIcons.PLUS))
                .add(new NavigationButton("Menu", VaadinIcons.MENU))
                .add(new NavigationButton("Elements", VaadinIcons.LIST))
                .addClickable("Click Me", VaadinIcons.QUESTION, clickEvent -> {/*Click Event*/})
                .add(new NavigationButton("Preferences", VaadinIcons.COG), FOOTER)
                .build();
        holder.addComponent(drawer);
    }

    ComboBox getVariantCombo(Behaviour variant) {
        ComboBox<Behaviour> variants = new ComboBox<>();
        variants.addStyleNames(ValoTheme.COMBOBOX_BORDERLESS, ValoTheme.CHECKBOX_SMALL, ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        variants.setWidth("300px");
        variants.setItems(Behaviour.LEFT,
                Behaviour.LEFT_OVERLAY,
                Behaviour.LEFT_RESPONSIVE,
                Behaviour.LEFT_RESPONSIVE_HYBRID,
                Behaviour.LEFT_RESPONSIVE_HYBRID_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_HYBRID_OVERLAY_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_OVERLAY,
                Behaviour.LEFT_RESPONSIVE_OVERLAY_NO_APP_BAR,
                Behaviour.LEFT_RESPONSIVE_SMALL,
                Behaviour.LEFT_RESPONSIVE_SMALL_NO_APP_BAR);
        variants.setValue(variant);
        variants.addValueChangeListener(valueChangeEvent -> setDrawerVariant(valueChangeEvent.getValue()));
        return variants;
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyUI.class)
    public static class Servlet extends VaadinServlet {
    }

    public static class View1 extends ExampleView { }

    public static class View2 extends ExampleView { }

    public static class View3 extends ExampleView { }

    public static class View4 extends ExampleView { }

    public static class View5 extends ExampleView { }

    public static class View6 extends ExampleView { }

    public static class View7 extends ExampleView { }

    public static class ExampleView extends HorizontalLayout implements View {
        public ExampleView() {
            addComponent(new Label(getClass().getSimpleName()));
        }
    }
}