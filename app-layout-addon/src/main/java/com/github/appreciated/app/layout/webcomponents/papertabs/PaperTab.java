package com.github.appreciated.app.layout.webcomponents.papertabs;

import com.github.appreciated.app.layout.webcomponents.appmenu.AppMenuIconItem;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Tag("paper-tab")
@HtmlImport("bower_components/paper-tabs/paper-tab.html")
public class PaperTab extends Component implements FlexComponent, HasText {

    private PaperTabs parent;

    public PaperTab(String caption, Icon icon) {
        setId("my-paper-tab");
        add(icon);
        setText(caption);
    }

    private void add(Component component) {
        if (component != null) {
            getElement().appendChild(component.getElement());
        }
    }

    public void setClickListener(ComponentEventListener<ClickEvent<AppMenuIconItem>> listener) {
        getElement().addEventListener("click", domEvent -> listener.onComponentEvent(null));
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

