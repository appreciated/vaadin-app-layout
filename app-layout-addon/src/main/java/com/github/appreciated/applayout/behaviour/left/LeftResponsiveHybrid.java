package com.github.appreciated.applayout.behaviour.left;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */

@Tag("app-layout-left-responsive-hybrid")
@HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-hybrid.html")
public class LeftResponsiveHybrid extends AbstractLeftAppLayoutBase {

    @Override
    public String getStyleName() {
        return "left-responsive-hybrid";
    }
}
