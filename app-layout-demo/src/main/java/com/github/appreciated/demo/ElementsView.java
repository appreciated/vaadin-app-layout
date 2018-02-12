package com.github.appreciated.demo;

import com.github.appreciated.demo.vaadin.elements.*;
import com.vaadin.annotations.HtmlImport;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ElementsView extends VerticalLayout implements View {


    public ElementsView(){
        VerticalLayout layout = new VerticalLayout();
        addDemo(layout, new Html5InputDemo(), "HTML5 inputs");
        // Does not work in Firefox
       // addDemo(layout,new GoogleMapDemo(), "Web components");
        addDemo(layout, new ExistingElementsDemo(), "Existing elements");
        addDemo(layout, new PaperElementsDemo(), "Paper compoments");
        layout.addComponent(new ComboBoxComponent("Test", "opsion1", "Opstion 2"));

        layout.setMargin(true);
        // layout.setSpacing(true);
        addComponent(layout);
        setSizeFull();
    }

    private void addDemo(VerticalLayout layout, AbstractElementsDemo demo,
                         String caption) {
        Label label = new Label(caption);
        label.addStyleName(ValoTheme.LABEL_H1);
        label.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        layout.addComponent(label);

        layout.addComponent(demo);
    }

}
