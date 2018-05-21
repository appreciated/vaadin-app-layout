package com.github.appreciated.app.layout.webcomponents.irondropdown;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class IronDropDownWrapper extends Div {

    private IronDropDown dropDown;

    public IronDropDownWrapper(Component button, Component content) {
        button.getElement().setAttribute("slot", "dropdown-trigger");
        content.getElement().setAttribute("slot", "dropdown-content");
        setButton(button);
        setContent(content);
    }

    public IronDropDownWrapper() {
    }

    public void setContent(Component content) {
        content.getElement().setAttribute("slot", "dropdown-content");
        dropDown = new IronDropDown(content);
        dropDown.getElement().addSynchronizedProperty("opened");
        add(dropDown);
    }

    public void setButton(Component button) {
        button.getElement().setAttribute("slot", "dropdown-trigger");
        button.getElement().addEventListener("click", domEvent -> {
            open();
        });
        add(button);
    }

    public void open() {
        dropDown.getElement().callFunction("open");
    }
}
