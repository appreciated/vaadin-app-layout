package com.github.appreciated;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainView.class)
public class View1 extends VerticalLayout {

    public View1() {
        Div d = new Div();
        d.getStyle().set("border", "1px black solid").set("padding", "10px").set("margin", "0px");
        add(d);
        setMargin(false);
        setPadding(false);
        setSpacing(false);
        setAlignItems(Alignment.STRETCH);
        setFlexGrow(1, d);
        setSizeFull();
    }

}