package com.github.appreciated.app.layout.component;

import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

public abstract class AppLayoutElement extends PolymerTemplate<AppLayoutElement.AppLayoutElementModel> {

    public AppLayoutElement(){

    }
    /**
     * Template model which defines where to implement the behaviour .
     */
    public interface AppLayoutElementModel extends TemplateModel  {

    }

}
