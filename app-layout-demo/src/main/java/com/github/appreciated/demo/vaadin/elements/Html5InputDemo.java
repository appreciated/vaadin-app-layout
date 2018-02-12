package com.github.appreciated.demo.vaadin.elements;

import java.util.Arrays;

import org.vaadin.elements.Element;
import org.vaadin.elements.ElementIntegration;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Root;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;

public class Html5InputDemo extends AbstractElementsDemo {

    private final NativeSelect<String> typeSelector = new NativeSelect<>(
            "Select input type",
            Arrays.asList("text", "search", "email", "url", "tel", "number",
                    "range", "date", "month", "week", "time", "datetime",
                    "datetime-local", "color"));
    private final Element input = Elements.create("input");
    private final CssLayout playground = new CssLayout();

    @Override
    protected String getDemoDescription() {
        return "This demo shows how to create an HTML element,"
                + " modify its attributes and send attribute values back to the server when an event occurs.";
    }

    @Override
    protected Component getDemoView() {
        input.bindAttribute("value", "change");
        input.addEventListener("change", arguments -> {
            Notification
                    .show("Value changed to " + input.getAttribute("value"));
        });

        typeSelector.setEmptySelectionAllowed(false);
        typeSelector.addValueChangeListener(event -> {
            String type = String.valueOf(typeSelector.getValue());

            input.setAttribute("value", "");
            input.setAttribute("type", type);

            playground.setCaption("input type=" + type);
        });
        typeSelector.setValue("range");

        Root root = ElementIntegration.getRoot(playground);
        root.appendChild(input);
        HorizontalLayout layout = new HorizontalLayout(typeSelector,
                playground);
        layout.setSpacing(true);
        return layout;
    }

}