package com.github.appreciated.app.layout.component;

import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.helper.LayoutHelper;
import com.github.appreciated.app.layout.webcomponents.ironcollapsebutton.IronCollapseButton;
import com.github.appreciated.app.layout.webcomponents.paperdrawer.PaperDrawerIconItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.github.appreciated.app.layout.builder.design.Styles.EXPANDING_MENU_SUBMENU_CONTAINER;

/**
 * The component which is used for submenu webcomponents. On click it toggles a css class which causes it to grow / shrink
 */
public class ExpandingMenuContainer extends IronCollapseButton implements SubmenuNavigationElement.SubmenuElement {

    private final VerticalLayout submenuWrapper;

    public ExpandingMenuContainer(String sectionName, Icon icon) {
        super();
        submenuWrapper = new VerticalLayout();
        submenuWrapper.setMargin(false);
        submenuWrapper.setPadding(false);
        submenuWrapper.getElement().getClassList().add(EXPANDING_MENU_SUBMENU_CONTAINER);
        submenuWrapper.setWidth("100%");
        LayoutHelper.disableFlex(submenuWrapper);
        setButton(new PaperDrawerIconItem(sectionName, "").getElement());
        setContent(submenuWrapper.getElement());
    }

    public void add(Component... components) {
        submenuWrapper.add(components);
    }

}
