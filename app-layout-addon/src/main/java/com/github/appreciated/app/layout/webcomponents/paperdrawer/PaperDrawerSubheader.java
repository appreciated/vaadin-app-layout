package com.github.appreciated.app.layout.webcomponents.paperdrawer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;

@Tag("paper-drawer-subheader")
@HtmlImport("bower_components/paper-drawer/paper-drawer-subheader.html")
public class PaperDrawerSubheader extends Component {
    public PaperDrawerSubheader(String title) {
        setTitle(title);
    }

    private void setTitle(String title) {
        getElement().appendChild(new Label(title).getElement());
    }
}



