package com.github.appreciated.applayout.component.appmenu.top;

import com.github.appreciated.applayout.webcomponents.papertabs.PaperTabs;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class TopMenu extends HorizontalLayout {
    private final PaperTabs tabs;

    public TopMenu() {
        tabs = new PaperTabs();
        getElement().getStyle()
                .set("flex-grow", "1")
                .set("flex-shrink", "1")
                .set("align-self", "flex-end");
        setWidth("100%");
        setHeight("var(--app-bar-height)");
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(tabs);
    }
}
