package com.github.appreciated.app.layout.webcomponents.papertabs;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.dom.Element;

@Tag("paper-tab")
@HtmlImport("bower_components/paper-tabs/paper-tab.html")
public class PaperTab extends Component implements FlexComponent {

    private PaperTabs parent;

    public PaperTab(String caption, String icon) {
        setId("my-paper-tab");
        add(new Icon(icon.split(":")[0], icon.split(":")[1]));
        setText(caption);
    }

    private void add(Component component) {
        if (component != null) {
            getElement().appendChild(new Element[]{component.getElement()});
        }
    }

    public void setText(String caption) {
        getElement().setText(caption);
    }

    private void setIcon(String string) {
    }

    public void setActive() {
        parent.setSelected(this);
    }

    public void setParent(PaperTabs parent) {
        this.parent = parent;
    }

}

