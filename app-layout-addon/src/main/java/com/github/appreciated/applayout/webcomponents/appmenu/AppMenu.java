package com.github.appreciated.applayout.webcomponents.appmenu;

import com.github.appreciated.applayout.helper.LayoutHelper;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;

@Tag("app-menu")
@HtmlImport("bower_components/app-menu/app-menu.html")
public class AppMenu extends Component implements HasComponents, FlexComponent {

    private AppSubmenu subparent;

    public AppMenu() {
    }

    public void setSelected(AppMenuItem selected) {
        if (subparent != null) {
            subparent.setSelected();
        }
        getElement().setAttribute("selected", String.valueOf(indexOf(selected)));
    }

    public void setSelected(AppSubmenu appMenu) {
        if (subparent != null) {
            subparent.setSelected();
        }
        getElement().setAttribute("selected", String.valueOf(indexOf(appMenu)));
    }

    @Override
    public void add(Component... components) {
        for (Component component : components) {
            if (component instanceof AppMenuItem) {
                ((AppMenuItem) component).setParent(this);
            } else if (component instanceof AppSubmenu) {
                ((AppSubmenu) component).setParent(this);
            }
        }
        LayoutHelper.add(this, components);
    }

    public void setParent(AppSubmenu appSubmenu) {
        this.subparent = appSubmenu;
    }


}
