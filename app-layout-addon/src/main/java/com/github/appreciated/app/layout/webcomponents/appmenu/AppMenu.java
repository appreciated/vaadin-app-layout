package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AppMenu extends VerticalLayout {
    private AppSubmenu subparent;

    @Override
    public void add(Component... components) {
        for (Component component : components) {
            if (component instanceof AppMenuIconItem) {
                ((AppMenuIconItem) component).setParent(this);
            } else if (component instanceof AppSubmenu) {
                ((AppSubmenu) component).setParent(this);
            }
        }
        super.add(components);
    }

    public void setParent(AppSubmenu appSubmenu) {
        this.subparent = appSubmenu;
    }

    public void setSelected(AppMenuItem appMenuItem) {

    }
}
