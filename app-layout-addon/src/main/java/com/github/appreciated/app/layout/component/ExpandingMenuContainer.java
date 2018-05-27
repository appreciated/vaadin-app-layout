package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.webcomponents.appmenu.AppSubmenu;
import com.vaadin.flow.component.icon.Icon;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer extends AppSubmenu implements SubmenuNavigationElement.SubmenuElement {

    public ExpandingMenuContainer(String sectionName, Icon icon) {
        super(sectionName, icon);
    }

}
