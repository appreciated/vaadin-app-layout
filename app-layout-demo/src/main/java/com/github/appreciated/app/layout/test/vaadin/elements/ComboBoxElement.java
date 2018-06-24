package com.github.appreciated.app.layout.test.vaadin.elements;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Tag;

@Tag("vaadin-combo-box")
public interface ComboBoxElement extends Element {

    static ComboBoxElement create() {
        return Elements.create(ComboBoxElement.class);
    }

    void setLabel(String label);

    void setItems(String items);

    String getValue();

    void setValue(String value);
}
