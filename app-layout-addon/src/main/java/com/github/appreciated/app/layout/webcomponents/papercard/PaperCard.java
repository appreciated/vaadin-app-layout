package com.github.appreciated.app.layout.webcomponents.papercard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;

@Tag("paper-card")
@HtmlImport("bower_components/paper-card/paper-card.html")
public class PaperCard extends Component {
    Div content;
    Div actions;

    public PaperCard(Component content) {
        this(content, null);
    }

    public PaperCard(Component content, Component actions) {
        add(content);
        if (actions != null) {
            addAction(actions);
        }
    }

    public void setHeader(String header) {
        getElement().setAttribute("heading", header);
    }

    public void add(Component component) {
        content.add(component);
        if (content == null) {
            content = new Div();
            content.getElement().getClassList().add("card-content");
            getElement().appendChild(content.getElement());
        }
        content.add(component);
    }

    public void addAction(Component action) {
        if (actions == null) {
            actions = new Div();
            actions.getElement().getClassList().add("card-actions");
            getElement().appendChild(actions.getElement());
        }
        actions.add(action);
    }

}