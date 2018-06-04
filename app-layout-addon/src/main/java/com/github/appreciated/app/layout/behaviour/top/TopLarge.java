package com.github.appreciated.app.layout.behaviour.top;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */
@Tag("app-layout-top-large")
@HtmlImport("frontend://com/github/appreciated/app-layout/top/top-large.html")
public class TopLarge extends Top {
    public TopLarge() {
        titleWrapper.setAlignItems(FlexComponent.Alignment.CENTER);
        titleWrapper.setHeight("var(--app-bar-height)");

        appBarElementWrapper.setAlignItems(FlexComponent.Alignment.START);
        appBarElementWrapper.setHeight("var(--app-bar-height)");
    }

}
