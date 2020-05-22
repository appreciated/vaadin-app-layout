package com.github.appreciated.app.layout.webcomponents.papertabs;

import com.github.appreciated.app.layout.component.menu.left.items.LeftIconItem;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Tag("paper-tab")
@JsModule("@polymer/paper-tabs/paper-tab.js")
@NpmPackage(value = "@polymer/paper-tabs", version = "3.1.0")
public class PaperTab extends Component implements FlexComponent, HasText {
    private static final long serialVersionUID = 1L;

    private PaperTabs parent;

    public PaperTab() {
        getElement().getStyle().set("font-size", "var(--app-layout-font-size-app-bar)");
    }

    void add(Component component) {
        if (component != null) {
            getElement().appendChild(component.getElement());
        }
    }

    public void setClickListener(ComponentEventListener<ClickEvent<LeftIconItem>> listener) {
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

