package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.elements.AppDrawer;
import com.github.appreciated.app.layout.elements.AppToolbar;
import com.github.appreciated.app.layout.elements.PaperIconButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

public class Left extends AbstractLeftAppLayoutBase {

    AppToolbar toolbar;
    PaperIconButton toggle;
    Div contentDiv;
    Div appBarElementsDiv;
    Div menuElementsDiv;
    AppDrawer drawer;

    /**
     * <app-toolbar style="height: var(--app-bar-height);">
     * <paper-icon-button id="toggle" icon="menu"></paper-icon-button>
     * <div id="app-bar-elements" class="app-bar-content"></div>
     * </app-toolbar>
     * <div class="app-bar-application-content" size="100"></div>
     * <app-drawer id="drawer" slot="drawer" swipe-open>
     * <div class="drawer-content"></div>
     * </app-drawer>
     *
     * @return
     */

    @Override
    public Component[] getContent() {
        toolbar = new AppToolbar();
        toggle = new PaperIconButton();
        contentDiv = new Div();
        appBarElementsDiv = new Div();
        menuElementsDiv = new Div();
        drawer = new AppDrawer();

        appBarElementsDiv.getElement().setAttribute("style", "height: var(--app-bar-height);");
        toolbar.getElement().appendChild(toggle.getElement());
        toolbar.getElement().appendChild(appBarElementsDiv.getElement());
        toggle.getElement().setAttribute("icon", "menu");
        toggle.setId("toggle");
        appBarElementsDiv.getElement().getClassList().add("app-bar-content");
        contentDiv.getElement().getClassList().add("app-bar-application-content");
        menuElementsDiv.getElement().getClassList().add("drawer-content");

        return new Component[]{toolbar, contentDiv, drawer};
    }

    @Override
    public Div getContentDiv() {
        return contentDiv;
    }

    @Override
    public Div getMenuElementsDiv() {
        return menuElementsDiv;
    }

    @Override
    public Div getAppBarElementsDiv() {
        return appBarElementsDiv;
    }

    @Override
    public String getStyleName() {
        return "left";
    }
}
