package com.github.appreciated.basic.test;

import com.github.appreciated.basic.test.vaadin.elements.*;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.elements.AbstractElementComponent;

public class ElementsView extends VerticalLayout implements View {


    public ElementsView() {
        VerticalLayout layout = new VerticalLayout();

        ComboBoxComponent comboBox = new ComboBoxComponent("Select an option",
                "Option 1", "Option 2", "Option 3");

        comboBox.setValue("Option 2");
        comboBox.addValueChangeListener(
                Notification::show);
        addComponent(layout, comboBox, "Comobox");

        addDemo(layout, new Html5InputDemo(), "HTML5 inputs");
        // Does not work in Firefox
        addDemo(layout, new GoogleMapDemo(), "Web components");
        addDemo(layout, new ExistingElementsDemo(), "Existing elements");
        addDemo(layout, new PaperElementsDemo(), "Paper compoments");

        layout.setMargin(true);
        Panel outer = new Panel(layout);
        outer.setSizeFull();
        addComponent(outer);
        setSizeFull();
    }

    private void addDemo(VerticalLayout layout, AbstractElementsDemo demo,
                         String caption) {
        Label label = new Label(caption);
        label.addStyleName(ValoTheme.LABEL_H1);
        //label.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        layout.addComponent(label);

        layout.addComponent(demo);
    }

    private void addComponent(VerticalLayout layout, AbstractElementComponent component,
                              String caption) {

        Label label = new Label(caption);
        label.addStyleName(ValoTheme.LABEL_H1);
        //label.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        layout.addComponent(label);

        layout.addComponent(component);
    }

}
