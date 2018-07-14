package com.github.appreciated.applayout.webcomponents.papertabs;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.dom.Element;

@Tag("paper-tab")
@HtmlImport("bower_components/paper-tabs/paper-tab.html")
public class PaperTab extends Component implements FlexComponent {

    private PaperTabs parent;

    public PaperTab(String caption, Icon icon, ComponentEventListener<ClickEvent<Button>> listener) {
        setId("my-paper-tab");
        add(icon);
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

