package com.github.appreciated.basic.test.vaadin.elements;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Tag;

@Tag("paper-button")
public interface PaperButton extends Element {
    public static PaperButton create() {
        return Elements.create(PaperButton.class);
    }

    public static PaperButton create(String text) {
        PaperButton button = create();
        button.setInnerText(text);
        return button;
    }

    public boolean isRaised();

    public void setRaised(boolean raised);

    public boolean isNoink();

    public void setNoink(boolean noink);
}
