package com.github.appreciated.app.layout.behaviour.left;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


/**
 * Created by appreciated on 01.05.2017.
 * Edited By deyaeddin on 07.02.2018
 */


@Tag("app-layout-left-responsive-small")
@HtmlImport("frontend://com/github/appreciated/app-layout/left/left-responsive-small.html")
public class LeftResponsiveSmall extends AbstractLeftAppLayoutBase {

    @Override
    public String getStyleName() {
        return "left-responsive-small";
    }
}
