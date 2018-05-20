package com.github.appreciated.app.layout.webcomponents.paperdrawer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;

@Tag("paper-drawer-item")
@HtmlImport("bower_components/paper-drawer/paper-drawer-item.html")
public class PaperDrawerItem extends Component {

    private ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener;

    public PaperDrawerItem(String title) {
        this(title, null);
    }

    public PaperDrawerItem(String title, ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener) {
        this.listener = listener;
        setTitle(title);
        getElement().addEventListener("click", domEvent -> {
            if (listener != null) {
                listener.onComponentEvent(new ClickEvent<>(this));
            }
        });
    }

    public void setClickListener(ComponentEventListener<ClickEvent<PaperDrawerIconItem>> listener) {
        this.listener = listener;
    }

    private void setTitle(String title) {
        getElement().appendChild(new Label(title).getElement());
    }
}
