package com.github.appreciated.app.layout.test.left;

import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.entity.Section;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayoutBase;
import com.github.appreciated.app.layout.test.left.view.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.ParentLayout;

@ParentLayout(MainLayout.class)
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
        LeftNavigationComponent home = new LeftNavigationComponent("View1", VaadinIcon.HOME.create(), getViewForI(1));
        LeftNavigationComponent menu = new LeftNavigationComponent("View9", VaadinIcon.MENU.create(), getViewForI(9));
        init(AppLayoutBuilder.get(Behaviour.LEFT)
                .withTitle("App Layout")
                .withIcon("frontend/images/logo.png")
                .withAppMenu(
                        LeftAppMenuBuilder.get()
                                .addToSection(new MenuHeaderComponent("App-Layout", "Version 2.0.8",
                                        "frontend/images/logo.png"), Section.HEADER)
                                .addToSection(new LeftClickableComponent("Set Behaviour HEADER", VaadinIcon.COG.create(), clickEvent -> {
                                }), Section.HEADER)
                                .add(home)
                                .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create())
                                        .add(new LeftNavigationComponent("View2", VaadinIcon.SPLINE_CHART.create(), getViewForI(2)))
                                        .add(new LeftNavigationComponent("View3", VaadinIcon.CONNECT.create(), getViewForI(3)))
                                        .add(new LeftNavigationComponent("View4", VaadinIcon.COG.create(), getViewForI(4)))
                                        .add(new LeftNavigationComponent("View5", VaadinIcon.CONNECT.create(), getViewForI(5)))
                                        .add(new LeftNavigationComponent("View6", VaadinIcon.COG.create(), getViewForI(6)))
                                        .build())
                                .add(new LeftNavigationComponent("View7", VaadinIcon.COG.create(), getViewForI(7)))
                                .add(new LeftNavigationComponent("View8", VaadinIcon.COG.create(), getViewForI(8)))
                                .add(menu)
                                .build()
                ).build());
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Component> getViewForI(int i) {
        return VIEWS[i - 1];
    }
}
