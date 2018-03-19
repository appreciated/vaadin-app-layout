package com.github.appreciated.demo.vaadin.elements;

import org.vaadin.elements.Element;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Tag;

@Tag(value = "div", exclusive = false)
public interface Layout extends Element {

    public static Layout vertical() {
        Layout layout = Elements.create(Layout.class);
        layout.setAttribute("layout", true);
        layout.setAttribute("vertical", true);
        return layout;
    }

    public static Layout horizontal() {
        Layout layout = Elements.create(Layout.class);
        layout.setAttribute("layout", true);
        layout.setAttribute("horizontal", true);
        return layout;
    }

    public boolean isHorizontal();

    public boolean isVertical();

    public boolean isFlex();

    public void setFlex(boolean flex);

    public boolean isCenter();

    public void setCenter(boolean center);

    public boolean isStart();

    public default void setStart(boolean start) {
        if (start) {
            removeAttribute("end");
        }
        setAttribute("start", start);
    }

    public default void setEnd(boolean end) {
        if (end) {
            removeAttribute("start");
        }
        setAttribute("end", end);
    }

    public boolean isJustified();

    public default void setJustified(boolean justified) {
        if (justified) {
            removeAttribute("around-justified");
        }
        setAttribute("justified", justified);
    }

    public default boolean isAroundJustified() {
        return hasAttribute("around-justified");
    }

    public default void setAroundJustified(boolean aroundJustified) {
        if (aroundJustified) {
            removeAttribute("justified");
        }
        setAttribute("around-justified", aroundJustified);
    }

}