package com.github.appreciated.app.layout.webcomponents.appmenu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.dom.Element;

@Tag("app-menu")
@HtmlImport("bower_components/app-menu/app-menu.html")
public class AppMenu extends Component implements HasComponents, FlexComponent {

    private AppSubmenu subparent;

    public AppMenu() {
    }

    public void setSelected(AppMenuItem selected) {
        if (subparent != null) {
            subparent.setSelected(this);
        }
        getElement().setAttribute("selected", String.valueOf(indexOf(selected)));
    }

    public void setSelected(AppSubmenu appMenu) {
        if (subparent != null) {
            subparent.setSelected(this);
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
        if (components == null) {
            throw new AssertionError();
        } else {
            Component[] var2 = components;
            int var3 = components.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Component component = var2[var4];
                if (component == null) {
                    throw new AssertionError();
                }
                this.getElement().appendChild(new Element[]{component.getElement()});
            }
        }
    }

    public void setParent(AppSubmenu appSubmenu) {
        this.subparent = appSubmenu;
    }


}
