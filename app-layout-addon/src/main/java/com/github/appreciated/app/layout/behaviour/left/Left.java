package com.github.appreciated.app.layout.behaviour.left;

import com.github.appreciated.app.layout.elements.AppDrawer;
import com.github.appreciated.app.layout.elements.AppToolbar;
import com.github.appreciated.app.layout.elements.PaperIconButton;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

public class Left extends AbstractLeftAppLayoutBase {

    AppToolbar toolbar;
    PaperIconButton toggle;
    HorizontalLayout contentDiv;
    HorizontalLayout appBarElementsDiv;
    VerticalLayout menuElementsDiv;
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
        contentDiv = new HorizontalLayout();
        appBarElementsDiv = new HorizontalLayout();
        menuElementsDiv = new VerticalLayout();
        drawer = new AppDrawer();

        toolbar.getElement().setAttribute("style", "height: var(--app-bar-height);");
        toolbar.getElement().appendChild(toggle.getElement());
        toolbar.getElement().appendChild(appBarElementsDiv.getElement());
        toggle.getElement().setAttribute("icon", "menu");
        toggle.setId("toggle");
        appBarElementsDiv.getElement().getClassList().add("app-bar-content");
        contentDiv.getElement().getClassList().add("app-bar-application-content");
        menuElementsDiv.getElement().getClassList().add("drawer-content");
        drawer.getElement().setAttribute("swipe-open", true);
        drawer.getElement().appendChild(menuElementsDiv.getElement());
        return new Component[]{toolbar, contentDiv, drawer};
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(ui -> ui.getPage().executeJavaScript(
                " var drawer = document.querySelector('app-drawer');\n" +
                        "        document.querySelector('paper-icon-button').addEventListener('click', function () {\n" +
                        "            drawer.toggle();\n" +
                        "        });")
        );
    }

    @Override
    public HasComponents getContentHolder() {
        return contentDiv;
    }

    @Override
    public HasComponents getMenuElementsHolder() {
        return menuElementsDiv;
    }

    @Override
    public HasComponents getAppBarElementsHolder() {
        return appBarElementsDiv;
    }

    @Override
    public String getStyleName() {
        return "left";
    }
}
