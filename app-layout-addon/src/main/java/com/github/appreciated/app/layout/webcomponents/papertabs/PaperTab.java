package com.github.appreciated.app.layout.webcomponents.papertabs;

import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.webcomponents.paperbadge.PaperBadge;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;

@Tag("paper-tab")
@HtmlImport("bower_components/paper-tabs/paper-tab.html")
public class PaperTab extends Component implements HasComponents {

    public PaperTab(String caption, String icon, DefaultBadgeHolder badgeHolder) {
        setId("my-paper-tab");
        add(new Icon(icon.split(":")[0], icon.split(":")[1]));
        setText(caption);
        if (badgeHolder != null) {
            add(new PaperBadge(this));
        }
    }

    public void setText(String caption) {
        getElement().setText(caption);
    }

    private void setIcon(String string) {
    }
}

