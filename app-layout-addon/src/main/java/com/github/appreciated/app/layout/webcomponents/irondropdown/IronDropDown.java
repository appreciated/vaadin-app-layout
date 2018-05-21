package com.github.appreciated.app.layout.webcomponents.irondropdown;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("iron-dropdown")
@HtmlImport("bower_components/iron-dropdown/iron-dropdown.html")
public class IronDropDown extends Component {

    public IronDropDown(Component component) {
        getElement().setAttribute("horizontal-align", "right")
                .setAttribute("vertical-align", "top");
        //getElement().getStyle().set("width", component.getElement().getStyle().get("width"));
        getElement().appendChild(component.getElement());
    }

}


