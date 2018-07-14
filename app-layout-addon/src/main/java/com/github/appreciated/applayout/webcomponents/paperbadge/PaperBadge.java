package com.github.appreciated.applayout.webcomponents.paperbadge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


@Tag("paper-badge")
@HtmlImport("bower_components/paper-badge/paper-badge.html")
public class PaperBadge extends Component {

    public PaperBadge(Component bind) {
        this(bind, null, null);
    }

    public PaperBadge(Component bind, String label) {
        this(bind, null, label);
    }

    public PaperBadge(Component bind, String icon, String label) {
        if (icon != null) {
            getElement().setAttribute("icon", icon);
        }
        if (label != null) {
            setLabel(label);
        }
        getElement().setAttribute("for", bind.getElement().getAttribute("id"));
    }

    public void setLabel(String label) {
        getElement().setAttribute("label", label);
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }
}
