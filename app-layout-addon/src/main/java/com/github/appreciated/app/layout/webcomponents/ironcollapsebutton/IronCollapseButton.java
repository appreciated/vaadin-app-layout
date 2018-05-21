package com.github.appreciated.app.layout.webcomponents.ironcollapsebutton;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;

@Tag("iron-collapse-button")
@HtmlImport("bower_components/iron-collapse-button/iron-collapse-button.html")
public class IronCollapseButton extends Component {
    private final Div collapseTrigger;
    private final Div collapseContent;

    public IronCollapseButton(Element button, Element content) {
        this();
        setButton(button);
        setContent(content);
    }

    public IronCollapseButton() {
        collapseTrigger = new Div();
        collapseTrigger.setWidth("100%");
        collapseTrigger.getElement().setAttribute("slot", "collapse-trigger");
        collapseTrigger.getStyle().set("margin-right", "-24px");
        collapseContent = new Div();
        collapseContent.getElement().setAttribute("slot", "collapse-content");
        getElement().appendChild(collapseTrigger.getElement());
        getElement().appendChild(collapseContent.getElement());
    }

    public void setContent(Element content) {
        collapseContent.getElement().appendChild(content);
    }

    public void setButton(Element button) {
        collapseTrigger.getElement().appendChild(button);
    }

    public void toggle() {
        setOpen(!getElement().hasAttribute("opened"));
    }

    public void setOpen(boolean open) {
        if (open) {
            getElement().setAttribute("opened", true);
        } else {
            getElement().removeAttribute("opened");
        }
    }
}


