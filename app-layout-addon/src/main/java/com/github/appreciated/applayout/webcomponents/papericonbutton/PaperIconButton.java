package com.github.appreciated.applayout.webcomponents.papericonbutton;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("paper-icon-button")
@HtmlImport("bower_components/iron-icons/iron-icons.html")
@HtmlImport("bower_components/paper-icon-button/paper-icon-button.html")
public class PaperIconButton extends Component implements HasSize {
    public PaperIconButton() {
    }

    public PaperIconButton(String icon) {
        setIcon(icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<PaperIconButton>> listener) {
        getElement().addEventListener("click", domEvent -> {
            if (listener != null) {
                listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }
}
