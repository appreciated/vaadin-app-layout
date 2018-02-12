package com.github.appreciated.demo.vaadin.elements;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Tag;
import org.vaadin.elements.UpdatedBy;

@Tag("paper-slider")
public interface PaperSlider extends Element {
    public static PaperSlider create() {
        return Elements.create(PaperSlider.class);
    }

    public void setValue(double value);

    @UpdatedBy("core-change")
    public double getValue();

}