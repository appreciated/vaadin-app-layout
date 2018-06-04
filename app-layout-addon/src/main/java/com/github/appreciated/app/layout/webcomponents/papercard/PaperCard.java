package com.github.appreciated.app.layout.webcomponents.papercard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Tag("paper-card")
@HtmlImport("bower_components/paper-card/paper-card.html")
public class PaperCard extends Component implements HasComponents, FlexComponent {
    Div content;
    Div actions;

    public PaperCard() {
        this(null, null);
    }

    public PaperCard(Component content) {
        this(content, null);
    }

    public PaperCard(Component content, Component actions) {
        if (content != null) {
            add(content);
        }
        if (actions != null) {
            addAction(actions);
        }
    }

    public void setHeader(String header) {
        getElement().setAttribute("heading", header);
    }

    @Override
    public void add(Component... components) {
        if (content == null) {
            content = new Div();
            content.getElement().getClassList().add("card-content");
            getElement().appendChild(content.getElement());
        }
        content.add(components);
    }

    public void addAction(Component... components) {
        if (actions == null) {
            actions = new Div();
            actions.getElement().getClassList().add("card-actions");
            getElement().appendChild(actions.getElement());
        }
        actions.add(components);
    }

}