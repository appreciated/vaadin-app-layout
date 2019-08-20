package com.github.appreciated.app.layout.test.nestedlayout;

import com.github.appreciated.app.layout.component.applayout.Behaviour;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayoutBase;
import com.github.appreciated.app.layout.entity.Section;
import com.github.appreciated.app.layout.test.nestedlayout.view.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;

@ParentLayout(MainLayout.class)
@RoutePrefix(absolute = true, value = "nestedlayout")
public class NestedLayout extends AppLayoutRouterLayoutBase {

    private static final Class[] VIEWS = {
            View1.class,
            View2.class,
            View3.class,
            View4.class,
            View5.class,
            View6.class,
            View7.class,
            View8.class,
            View9.class
    };

    public NestedLayout() {
        LeftNavigationItem home = new LeftNavigationItem("View1", VaadinIcon.HOME.create(), getViewForI(1));
        LeftNavigationItem menu = new LeftNavigationItem("View9", VaadinIcon.MENU.create(), getViewForI(9));
        init(AppLayoutBuilder.get(Behaviour.LEFT)
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(new LeftHeaderItem("App-Layout", "Version 3.0.0",
                                        "frontend/images/logo.png"), Section.HEADER)
                                .addToSection(new LeftClickableItem("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                }), Section.HEADER)
                                .add(home)
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationItem("View2", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)))
                                        .add(new LeftNavigationItem("View3", VaadinIcon.CONNECT.create(), getViewForI(3)))
                                        .add(new LeftNavigationItem("View4", VaadinIcon.COG.create(), getViewForI(4)))
                                        .add(new LeftNavigationItem("View5", VaadinIcon.CONNECT.create(), getViewForI(5)))
                                        .add(new LeftNavigationItem("View6", VaadinIcon.COG.create(), getViewForI(6)))
                                        .build())
                                .add(new LeftNavigationItem("View7", VaadinIcon.COG.create(), getViewForI(7)))
                                .add(new LeftNavigationItem("View8", VaadinIcon.COG.create(), getViewForI(8)))
                                .add(menu)
                                .build()
                ).build());
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Component> getViewForI(int i) {
        return VIEWS[i - 1];
    }
}
