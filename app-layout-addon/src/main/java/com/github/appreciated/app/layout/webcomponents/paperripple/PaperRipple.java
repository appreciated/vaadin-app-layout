package com.github.appreciated.app.layout.webcomponents.paperripple;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;


@Tag("paper-ripple")
@HtmlImport("bower_components/paper-ripple/paper-ripple.html")
public class PaperRipple extends Component {

    public PaperRipple() {
        this(false, false, false);
    }

    public PaperRipple(boolean circle, boolean recenters, boolean noink) {
        setCircle(circle);
        setRecenters(recenters);
        setNoInk(noink);
    }

    public void setNoInk(boolean noink) {
        getElement().setAttribute("noink", noink);
    }

    public void setRecenters(boolean recenters) {
        getElement().setAttribute("recenters", recenters);
    }

    public void setCircle(boolean circle) {
        getElement().getClassList().set("circle", circle);
    }


}
