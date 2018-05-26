package com.github.appreciated.app.layout.behaviour.top;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;


/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */


@HtmlImport("src/com/github/appreciated/app/layout/behaviour/top/top-large.html")
public class TopLarge extends AbstractTopAppLayoutBase {

    @Override
    public void addToTopHeader(Component component) {
        getAppBar().add(component);
    }

    @Override
    public void addToTop(Component component) {
        getAppBar().add(component);
    }

    @Override
    public void addToTopFooter(Component component) {
        getAppBar().add(component);
    }

    @Override
    public String getStyleName() {
        return "top-large";
    }
}
