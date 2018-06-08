package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.webcomponents.appmenu.AppSubmenu;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.icon.Icon;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer extends AppSubmenu implements SubmenuNavigationElement.SubmenuElement, NavigationElementContainer {

    public ExpandingMenuContainer(String sectionName, Icon icon) {
        super(sectionName, icon);
        getItem().getElement().getStyle().set("white-space", "nowrap");
    }

    @Override
    public boolean setActiveNavigationElementWithViewClass(HasElement element) {
        return getMenu().getChildren()
                .filter(component -> component instanceof NavigationElementContainer)
                .map(component -> ((NavigationElementContainer) component).setActiveNavigationElementWithViewClass(element))
                .reduce((first, next) -> first || next).orElse(false);
    }
}
