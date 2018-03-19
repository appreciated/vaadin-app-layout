package com.github.appreciated.demo.vaadin.elements;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.ui.*;
import org.vaadin.elements.Element;
import org.vaadin.elements.ElementIntegration;
import org.vaadin.elements.Elements;
import org.vaadin.elements.Root;

@HtmlImport("vaadin://frontend/bower_components/google-map/google-map.html")
public class GoogleMapDemo extends AbstractElementsDemo {

    @Override
    protected String getDemoDescription() {
        return "Maps!";
    }

    @Override
    protected Component getDemoView() {
        CssLayout wrapper = new CssLayout();
        Root root = ElementIntegration.getRoot(wrapper);

        final Element googleMap = Elements.create("google-map");

        googleMap.setAttribute("style",
                "height: 500px; width: 500px; display: block");
        googleMap.setAttribute("latitude", "60.45235");
        googleMap.setAttribute("longitude", "22.299727");
        googleMap.setAttribute("zoom", "17");

        root.appendChild(googleMap);

        HorizontalLayout buttons = new HorizontalLayout(
                new Button("GWT.create US", event -> {
                    googleMap.setAttribute("latitude", "37.414274");
                    googleMap.setAttribute("longitude", "-122.077409");
                }), new Button("GWT.create EU", event -> {
            googleMap.setAttribute("latitude", "48.152663");
            googleMap.setAttribute("longitude", "11.598418");
        }));
        buttons.setSpacing(true);

        VerticalLayout layout = new VerticalLayout(buttons, wrapper);
        layout.setSpacing(true);
        return layout;
    }

}
