package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Tag("app-menu")
@HtmlImport("bower_components/app-menu/app-menu.html")
public class AppMenu extends Component implements HasComponents, FlexComponent {

    public AppMenu() {
    }

    public void setSelected(int selected) {
        getElement().setAttribute("selected", String.valueOf(selected));
    }

    public void add(Component component) {
        getElement().appendChild(component.getElement());
    }
}
