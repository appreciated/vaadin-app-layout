package com.github.appreciated.app.layout.test.vaadin.elements;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import org.vaadin.elements.ElementIntegration;
import org.vaadin.elements.Root;

@HtmlImport("vaadin://frontend/bower_components/paper-button/paper-button.html")
@HtmlImport("vaadin://frontend/bower_components/paper-slider/paper-slider.html")
public class PaperElementsDemo extends AbstractElementsDemo {

    @Override
    protected String getDemoDescription() {
        return "This demo shows how to create custom Element types to provide a type-safe API for modifying the element's attributes.";
    }

    @Override
    protected Component getDemoView() {
        PaperButton basicButton = PaperButton.create("Basic button");
        basicButton.setRaised(true);
        basicButton.addEventListener("click", args -> {
            Notification.show("Clicked");
        });

        PaperButton notRaisedButton = PaperButton.create("Not raised");
        notRaisedButton.setRaised(false);

        PaperButton noInkButton = PaperButton.create("No ink");
        noInkButton.setRaised(true);
        noInkButton.setNoink(true);

        PaperButton disabledButton = PaperButton.create("Disabled");
        disabledButton.setDisabled(true);

        Layout horizontal = Layout.horizontal();
        horizontal.setJustified(true);
        horizontal.setAttribute("style", "width: 600px");

        horizontal.appendChild(basicButton);
        horizontal.appendChild(notRaisedButton);
        horizontal.appendChild(noInkButton);
        horizontal.appendChild(disabledButton);

        PaperSlider slider = PaperSlider.create();
        slider.setValue(50);
        slider.addEventListener("change", arguments -> {
            Notification.show("Value changed to " + slider.getValue());
        });

        Layout vertical = Layout.vertical();
        vertical.appendHtml("<h2>Slider</h2>");
        vertical.appendChild(slider);
        vertical.appendHtml("<h2>Buttons</h2>");
        vertical.appendChild(horizontal);

        CssLayout wrapper = new CssLayout();
        Root root = ElementIntegration.getRoot(wrapper);
        root.appendChild(vertical);
        return wrapper;
    }

}