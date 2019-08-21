package com.github.appreciated.app.layout.webcomponents.paperbadge;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;


@Tag("paper-badge")
@NpmPackage(value = "@polymer/paper-badge", version = "3.0.1")
@JsModule("@polymer/paper-badge/paper-badge.js")
public class PaperBadge extends Component implements HasText {

    public PaperBadge() {
        this(null, null);
    }

    public PaperBadge(String label) {
        this(null, label);
    }

    public PaperBadge(String icon, String label) {
        if (icon != null) {
            getElement().setAttribute("icon", icon);
        }
        if (label != null) {
            setText(label);
        }
    }

    @Override
    public void setText(String label) {
        getElement().setAttribute("label", label);
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }

    public void notifyResize() {
        getElement().callJsFunction("notifyResize");
    }
}
