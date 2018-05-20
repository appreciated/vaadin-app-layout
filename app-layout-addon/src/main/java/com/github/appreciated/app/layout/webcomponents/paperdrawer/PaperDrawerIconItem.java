package com.github.appreciated.app.layout.webcomponents.paperdrawer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;

@Tag("paper-drawer-icon-item")
@HtmlImport("bower_components/paper-drawer/paper-drawer-icon-item.html")
public class PaperDrawerIconItem extends Component {

    private ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener;

    public PaperDrawerIconItem(String title, String icon) {
        this(title, icon, null);
    }

    public PaperDrawerIconItem(String title, String icon, ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener) {
        this.listener = listener;
        setTitle(title);
        setIcon(icon);
        getElement().addEventListener("click", domEvent -> {
            if (this.listener != null) {
                this.listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public void setClickListener(ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener) {
        this.listener = listener;
    }

    public void setIcon(String icon) {
        getElement().setAttribute("icon", icon);
    }

    public void setTitle(String title) {
        getElement().appendChild(new Label(title).getElement());
    }
}
