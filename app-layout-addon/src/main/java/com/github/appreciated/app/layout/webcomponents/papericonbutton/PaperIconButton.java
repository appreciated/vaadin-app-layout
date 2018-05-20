package com.github.appreciated.app.layout.webcomponents.papericonbutton;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("paper-icon-button")
@HtmlImport("bower_components/iron-icons/iron-icons.html")
@HtmlImport("bower_components/paper-icon-button/paper-icon-button.html")
public class PaperIconButton extends Component {
    public PaperIconButton() {
    }

    public PaperIconButton(String icon) {
        getElement().setAttribute("icon", icon);
    }

    public void setClickListener(ComponentEventListener<ClickEvent<PaperIconButton>> listener) {
        getElement().addEventListener("click", domEvent -> {
            if (listener != null) {
                listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }
}
